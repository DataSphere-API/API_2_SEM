package org.datasphere.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.datasphere.dao.AulaPlanejadaDAO;
import org.datasphere.dao.MateriaDAO;
import org.datasphere.dao.TopicoDAO;
import org.datasphere.dao.interfaces.IDAO;
import org.datasphere.model.*;
import org.datasphere.service.AulaService;
import org.datasphere.service.OrganizarAulaService;
import org.datasphere.service.SemestreService;
import org.datasphere.service.TopicoService;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

public class CadastrarAulaController {

    @FXML private TableColumn<TopicoModel, String> clnTopico;
    @FXML private TableColumn<TopicoModel, String> clnAulasTopico;

    @FXML private CheckBox chkProva;
    @FXML private Spinner<Integer> spnrMinAulas;
    @FXML private Spinner<Integer> spnrMaxAulas;

    @FXML private Label lbNomeUsuario;
    @FXML private Label lbContadorCargaHoraria;
    @FXML private Label lbContadorHorasPlanejadas;
    @FXML private Label lbContadorHorasFaltantes;

    @FXML private TableView<TopicoModel> tblTopicoAdicionado;
    @FXML private TextField txtFldTituloTopico;

    @FXML private Button btExcluir;

    @FXML private CheckBox chkSeg1845, chkSeg1935, chkSeg2025, chkSeg2115, chkSeg2205;
    @FXML private CheckBox chkTer1845, chkTer1935, chkTer2025, chkTer2115, chkTer2205;
    @FXML private CheckBox chkQua1845, chkQua1935, chkQua2025, chkQua2115, chkQua2205;
    @FXML private CheckBox chkQui1845, chkQui1935, chkQui2025, chkQui2115, chkQui2205;
    @FXML private CheckBox chkSex1845, chkSex1935, chkSex2025, chkSex2115, chkSex2205;

    @FXML private TableColumn<AulaPlanejada, String> clnAulasPlanejamento;
    @FXML private TableColumn<AulaPlanejada, String> clnDataPlanejamento;
    @FXML private TableColumn<AulaPlanejada, String> clnTopicoPlanejamento;
    @FXML private TableView<AulaPlanejada> tblPlanejamentoAulas;

    // ComboBox para selecionar a matéria
    @FXML private ComboBox<MateriaModel> cmbSelecionarMateria;

    private ObservableList<TopicoModel> obsListTopicos;
    private ObservableList<AulaPlanejada> obsListAulasPlanejadas;

    private AulaPlanejadaDAO aulaPlanejadaDAO = new AulaPlanejadaDAO();
    private IDAO<TopicoModel> topicoDAO = new TopicoDAO();
    private MateriaDAO materiaDAO = new MateriaDAO();
    private TopicoService topicoService = new TopicoService();
    private AulaService aulaService = new AulaService();
    private SemestreService semestreService = new SemestreService();
    private OrganizarAulaService organizarAulaService = new OrganizarAulaService();

    // Matéria atualmente selecionada
    private MateriaModel materiaSelecionada;

    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private int cargaHorariaMateria = 0;

    public void initialize() {
        spnrMinAulas.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1));
        spnrMaxAulas.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 2));

        ProfessorModel profLogado = SessaoUsuario.getSessao().getProfessorLogado();
        if (profLogado != null) {
            lbNomeUsuario.setText(profLogado.getNome());
            carregarMateriasProfessor(profLogado.getEmail());
        }

        semestreService.criarSemestreComDias();
        criarSemanaSprint();

        obsListTopicos = FXCollections.observableArrayList();
        tblTopicoAdicionado.setItems(obsListTopicos);

        obsListAulasPlanejadas = FXCollections.observableArrayList();
        tblPlanejamentoAulas.setItems(obsListAulasPlanejadas);

        configurarTabelaTopicos();
        configurarTabelaPlanejamento();

        chkProva.selectedProperty().addListener((observable, oldValue, newValue) -> {
            spnrMinAulas.setDisable(newValue);
            spnrMaxAulas.setDisable(newValue);
            if (newValue) {
                spnrMinAulas.getValueFactory().setValue(0);
                spnrMaxAulas.getValueFactory().setValue(0);
            } else {
                spnrMinAulas.getValueFactory().setValue(1);
                spnrMaxAulas.getValueFactory().setValue(2);
            }
        });
    }

    /**
     * Carrega as matérias do professor logado no ComboBox.
     * Exibe o formato "SIGLA - Título" para o usuário.
     */
    private void carregarMateriasProfessor(String emailProfessor) {
        List<MateriaModel> materias = materiaDAO.listarPorEmailProfessor(emailProfessor);

        // Define como o ComboBox exibe cada matéria
        cmbSelecionarMateria.setConverter(new javafx.util.StringConverter<MateriaModel>() {
            @Override
            public String toString(MateriaModel m) {
                if (m == null) return "";
                return m.getSigla() + " - " + m.getTitulo();
            }
            @Override
            public MateriaModel fromString(String s) { return null; }
        });

        cmbSelecionarMateria.setItems(FXCollections.observableArrayList(materias));

        // Se só há uma matéria, seleciona automaticamente e já carrega o planejamento
        if (materias.size() == 1) {
            cmbSelecionarMateria.getSelectionModel().selectFirst();
            aplicarSelecaoMateria(materias.get(0));
        }
    }

    /**
     * Chamado pelo FXML quando o usuário muda a seleção no ComboBox de matérias.
     * Recarrega do banco os contadores e o Planejamento de Aulas da matéria escolhida.
     */
    @FXML
    private void selecionarMateria(ActionEvent event) {
        MateriaModel materiaSelecionada = cmbSelecionarMateria.getSelectionModel().getSelectedItem();
        if (materiaSelecionada != null) {
            aplicarSelecaoMateria(materiaSelecionada);
        }
    }

    /**
     * Aplica a seleção de uma matéria: atualiza contadores e recarrega
     * as aulas planejadas persistidas no banco para essa matéria.
     */
    private void aplicarSelecaoMateria(MateriaModel materia) {
        this.materiaSelecionada = materia;
        cargaHorariaMateria = materia.getCargaHoraria();
        lbContadorCargaHoraria.setText(cargaHorariaMateria + "h");

        // Recarrega as aulas planejadas do banco para esta matéria
        carregarAulasPlanejadas(materia.getSigla());
    }

    /**
     * Busca as aulas planejadas do banco para a matéria informada e
     * popula a tabela de Planejamento de Aulas + atualiza os contadores.
     */
    private void carregarAulasPlanejadas(String siglaMateria) {
        List<AulaPlanejada> aulas = aulaPlanejadaDAO.listarPorSiglaMateria(siglaMateria);

        obsListAulasPlanejadas.clear();
        obsListAulasPlanejadas.addAll(aulas);

        int horasAgendadas = aulas.size();
        int horasFaltantes = Math.max(0, cargaHorariaMateria - horasAgendadas);

        lbContadorHorasPlanejadas.setText(horasAgendadas + "h");
        lbContadorHorasFaltantes.setText(horasFaltantes + "h");
    }

    private void configurarTabelaTopicos() {
        clnTopico.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTitulo())
        );

        clnAulasTopico.setCellValueFactory(cellData -> {
            TopicoModel t = cellData.getValue();
            if (t.isProva()) return new javafx.beans.property.SimpleStringProperty("PROVA");
            return new javafx.beans.property.SimpleStringProperty(t.getAulasMinimas() + " a " + t.getAulasMaximas());
        });
    }

    private void configurarTabelaPlanejamento() {
        clnAulasPlanejamento.setCellValueFactory(cellData -> {
            AulaPlanejada ap = cellData.getValue();
            String dia = ap.getAulaModel().getDiaDaSemana()
                    .getDisplayName(TextStyle.SHORT, new Locale("pt", "BR"));
            String hora = ap.getAulaModel().getHoraInicio() + " - " + ap.getAulaModel().getHoraFim();
            return new javafx.beans.property.SimpleStringProperty(dia + " " + hora);
        });

        clnTopicoPlanejamento.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getTopicoModel().getTitulo())
        );

        clnDataPlanejamento.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getDiaModel().getData().format(fmt))
        );
    }

    @FXML
    private void cadastrarTopico() {
        String titulo = txtFldTituloTopico.getText();
        if (titulo != null && !titulo.isEmpty()) {
            TopicoModel novoTopico = topicoService.cadastrar(titulo, spnrMinAulas.getValue(), spnrMaxAulas.getValue(), chkProva.isSelected(),  materiaSelecionada.getSigla());
            obsListTopicos.add(novoTopico);
            txtFldTituloTopico.clear();
        }
    }

    @FXML
    private void excluirTopico(ActionEvent event) {
        TopicoModel topicoSelecionado = tblTopicoAdicionado.getSelectionModel().getSelectedItem();

        if (topicoSelecionado != null) {
            obsListTopicos.remove(topicoSelecionado);
        } else {
            obsListTopicos.clear();
        }
    }

    public void criarSemanaSprint() {
        for (int i = 6; i < 14; i++) {
            semestreService.getSemestre().getDiasList().get(i).setDisponivelParaProva(false);
        }
    }

    public List<AulaModel> lerDiaHorarioAula() {
        List<AulaModel> diasDeAula = new LinkedList<>();
        List<LocalTime> tempos = List.of(
                LocalTime.of(18, 45), LocalTime.of(19, 35), LocalTime.of(20, 25),
                LocalTime.of(21, 15), LocalTime.of(22, 5), LocalTime.of(23, 5));

        Map<DayOfWeek, List<CheckBox>> horariosPorDia = new LinkedHashMap<>();
        horariosPorDia.put(DayOfWeek.MONDAY,    List.of(chkSeg1845, chkSeg1935, chkSeg2025, chkSeg2115, chkSeg2205));
        horariosPorDia.put(DayOfWeek.TUESDAY,   List.of(chkTer1845, chkTer1935, chkTer2025, chkTer2115, chkTer2205));
        horariosPorDia.put(DayOfWeek.WEDNESDAY, List.of(chkQua1845, chkQua1935, chkQua2025, chkQua2115, chkQua2205));
        horariosPorDia.put(DayOfWeek.THURSDAY,  List.of(chkQui1845, chkQui1935, chkQui2025, chkQui2115, chkQui2205));
        horariosPorDia.put(DayOfWeek.FRIDAY,    List.of(chkSex1845, chkSex1935, chkSex2025, chkSex2115, chkSex2205));

        for (Map.Entry<DayOfWeek, List<CheckBox>> entrada : horariosPorDia.entrySet()) {
            DayOfWeek diaDaSemana = entrada.getKey();
            List<CheckBox> horarios = entrada.getValue();

            for (int i = 0; i < horarios.size(); i++) {
                if (horarios.get(i).isSelected()) {
                    diasDeAula.add(new AulaModel(diaDaSemana, tempos.get(i), tempos.get(i + 1)));
                }
            }
        }
        aulaService.salvarAulas(diasDeAula);
        return diasDeAula;
    }

    private void adicionarTopicoLista() {
        List<AulaPlanejada> aulasPlanejadas = organizarAulaService.organizarAulas(
                obsListTopicos, lerDiaHorarioAula(), semestreService.getSemestre(), cargaHorariaMateria);

        obsListAulasPlanejadas.clear();
        obsListAulasPlanejadas.addAll(aulasPlanejadas);

        int horasAgendadas = aulasPlanejadas.size();
        int horasFaltantes = Math.max(0, cargaHorariaMateria - horasAgendadas);

        lbContadorHorasPlanejadas.setText(horasAgendadas + "h");
        lbContadorHorasFaltantes.setText(horasFaltantes + "h");
    }

    @FXML
    private void gerarPlanejamentoPlanilha(ActionEvent event) {
        adicionarTopicoLista();
    }

    @FXML
    void baixarArquivo(ActionEvent event) {

        ObservableList<AulaPlanejada> itensDaTabela = tblPlanejamentoAulas.getItems();

        LinkedList<AulaPlanejada> listaParaExportar = new LinkedList<>(itensDaTabela);

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        OrganizarAulaService.exportarPlanejamento(listaParaExportar, stage);

    }
}
