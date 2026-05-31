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
import javafx.stage.Stage;
import org.datasphere.dao.AulaPlanejadaDAO;
import org.datasphere.dao.DiaDAO;
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
import java.time.LocalDate;
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

    @FXML private ComboBox<MateriaModel> cmbSelecionarMateria;

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

    private ObservableList<TopicoModel> obsListTopicos;
    private ObservableList<AulaPlanejada> obsListAulasPlanejadas;

    private AulaPlanejadaDAO aulaPlanejadaDAO = new AulaPlanejadaDAO();
    private IDAO<TopicoModel> topicoDAO = new TopicoDAO();
    private MateriaDAO materiaDAO = new MateriaDAO();
    private TopicoService topicoService = new TopicoService();
    private AulaService aulaService = new AulaService();
    private SemestreService semestreService = new SemestreService();
    private OrganizarAulaService organizarAulaService = new OrganizarAulaService();
    private DiaDAO diaDAO = new DiaDAO();

    private MateriaModel materiaSelecionada;

    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private int cargaHorariaMateria = 0;

    public void initialize() {
        spnrMinAulas.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1));
        spnrMaxAulas.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 2));

        obsListTopicos = FXCollections.observableArrayList();
        tblTopicoAdicionado.setItems(obsListTopicos);

        obsListAulasPlanejadas = FXCollections.observableArrayList();
        tblPlanejamentoAulas.setItems(obsListAulasPlanejadas);

        configurarTabelaTopicos();
        configurarTabelaPlanejamento();

        ProfessorModel profLogado = SessaoUsuario.getSessao().getProfessorLogado();
        if (profLogado != null) {
            lbNomeUsuario.setText(profLogado.getNome());
            carregarMateriasProfessor(profLogado.getEmail());
        }

        List<DiaModel> diasExistentes = diaDAO.listar();

        if (!diasExistentes.isEmpty()) {
            List<DiaModel> diasLetivos = diasExistentes.stream()
                    .filter(d -> d.getTitulo() == null
                            || d.getTitulo().isBlank()
                            || d.getTitulo().equals("Período de Sprint"))
                    .collect(java.util.stream.Collectors.toList());

            if (!diasLetivos.isEmpty()) {
                SemestreModel semestreExistente = new SemestreModel(
                        diasLetivos.get(0).getData(),
                        diasLetivos.get(diasLetivos.size() - 1).getData()
                );
                diasLetivos.forEach(semestreExistente::adicionarDias);
                semestreService.setSemestre(semestreExistente);
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "O período letivo não foi configurado pelo coordenador ainda", ButtonType.OK).show();
        }

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

    private void carregarMateriasProfessor(String emailProfessor) {
        List<MateriaModel> materias = materiaDAO.listarPorEmailProfessor(emailProfessor);

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

        if (materias.size() == 1) {
            cmbSelecionarMateria.getSelectionModel().selectFirst();
            aplicarSelecaoMateria(materias.get(0));
        }
    }

    @FXML
    private void selecionarMateria(ActionEvent event) {
        MateriaModel selecionada = cmbSelecionarMateria.getSelectionModel().getSelectedItem();
        if (selecionada != null) {
            aplicarSelecaoMateria(selecionada);
        }
    }

    private void aplicarSelecaoMateria(MateriaModel materia) {
        this.materiaSelecionada = materia;
        this.cargaHorariaMateria = materia.getCargaHoraria();

        lbContadorCargaHoraria.setText(cargaHorariaMateria + "h");

        obsListTopicos.clear();
        carregarAulasPlanejadas(materia.getSigla());
    }

    private void carregarAulasPlanejadas(String siglaMateria) {
        List<AulaPlanejada> aulas = aulaPlanejadaDAO.listarPorSiglaMateria(siglaMateria);

        obsListAulasPlanejadas.setAll(aulas);

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
        if (materiaSelecionada == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, selecione uma matéria primeiro!", ButtonType.OK);
            alert.show();
            return;
        }

        String titulo = txtFldTituloTopico.getText();
        if (titulo != null && !titulo.isEmpty()) {
            TopicoModel novoTopico = topicoService.cadastrar(
                    titulo,
                    spnrMinAulas.getValue(),
                    spnrMaxAulas.getValue(),
                    chkProva.isSelected(),
                    materiaSelecionada.getSigla()
            );
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
        if (materiaSelecionada == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, selecione uma matéria primeiro!", ButtonType.OK);
            alert.show();
            return;
        }

        List<AulaPlanejada> aulasPlanejadas = organizarAulaService.organizarAulas(
                obsListTopicos, lerDiaHorarioAula(), semestreService.getSemestre(), cargaHorariaMateria);

        obsListAulasPlanejadas.setAll(aulasPlanejadas);

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
        LinkedList<AulaPlanejada> listaParaExportar = new LinkedList<>(tblPlanejamentoAulas.getItems());
        javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        OrganizarAulaService.exportarPlanejamento(listaParaExportar, stage);
    }

    @FXML
    void handleLogout(ActionEvent event) {
        SessaoUsuario.getSessao().limparSessao();
        try {
            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageAtual.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/datasphere/login.fxml"));
            Parent root = loader.load();

            Stage loginStage = new Stage();
            loginStage.setTitle("DataSphere - Login");
            loginStage.setScene(new Scene(root));
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}