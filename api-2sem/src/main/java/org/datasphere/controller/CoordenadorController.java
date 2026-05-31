package org.datasphere.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.datasphere.dao.CadastroDAO;
import org.datasphere.dao.MateriaDAO;
import org.datasphere.model.DiaModel;
import org.datasphere.model.MateriaModel;
import org.datasphere.model.ProfessorModel;
import org.datasphere.model.SemestreModel;
import org.datasphere.model.SessaoUsuario;
import org.datasphere.service.SemestreService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class CoordenadorController {

    @FXML private Button btAdicionarData;
    @FXML private Button btAdicionarPeriodo;
    @FXML private Button btAdicionarPeriodoSprint;
    @FXML private Button btSair;
    @FXML private Button btnCadastrarDisciplina;

    @FXML private TableColumn<DiaModel, String> clnData;
    @FXML private TableColumn<DiaModel, String> clnTitulo;
    @FXML private TableColumn<DiaModel, String> clnDescricao;

    @FXML private TableColumn<MateriaModel, String> clnNomeDisciplina;
    @FXML private TableColumn<MateriaModel, String> clnCargaHoraria;
    @FXML private TableColumn<MateriaModel, String> clnCurso;

    @FXML private ColorPicker cpNovaLegendaCor;
    @FXML private DatePicker dpDataFinal;
    @FXML private DatePicker dpDataInicial;
    @FXML private DatePicker dpDataInicialSprint;
    @FXML private DatePicker dpDataFinalSprint;
    @FXML private DatePicker dpFeriados;

    @FXML private HBox hbBotoesDisciplina;
    @FXML private ImageView ivLogo;

    @FXML private RadioButton rb40Horas;
    @FXML private RadioButton rb80Horas;
    @FXML private ToggleGroup grupoHorario;

    @FXML private Tab tabCalendario;
    @FXML private Tab tabDisciplinas;

    @FXML private TableView<DiaModel> tblDatasImportantes;
    @FXML private TableView<MateriaModel> tblDisciplinas;

    @FXML private Text txtContadorDatas;
    @FXML private Text txtContadorDisciplinas;
    @FXML private Text txtTotalDatas;
    @FXML private Text txtTotalDisciplinas;
    @FXML private Text txtInfo;
    @FXML private Text txtTituloDisciplina;

    @FXML private TextField txtCurso;
    @FXML private TextField txtFiltroDisciplina;
    @FXML private TextField txtIdDisciplina;
    @FXML private TextField txtNomeDisciplina;
    @FXML private TextField txtNovaLegendaNome;
    @FXML private TextField txtTituloData;
    @FXML private TextArea txtDescricaoData;

    @FXML private VBox vbNovaLegenda;
    @FXML private ComboBox<ProfessorModel> cmbProfessor;

    private CadastroDAO cadastroDAO = new CadastroDAO();
    private MateriaDAO materiaDAO = new MateriaDAO();

    private ObservableList<DiaModel> obsDatas = FXCollections.observableArrayList();
    private ObservableList<MateriaModel> obsDisciplinas = FXCollections.observableArrayList();

    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void initialize() {
        carregarProfessores();
        configurarTabelaDatas();
        configurarTabelaDisciplinas();
        carregarDatas();
        carregarDisciplinas();
    }

    private void configurarTabelaDatas() {
        clnData.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(
                        cell.getValue().getData() != null ? cell.getValue().getData().format(fmt) : ""));

        clnTitulo.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(
                        cell.getValue().getTitulo() != null ? cell.getValue().getTitulo() : ""));

        clnDescricao.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(
                        cell.getValue().getDescricao() != null ? cell.getValue().getDescricao() : ""));

        tblDatasImportantes.setItems(obsDatas);
    }

    private void configurarTabelaDisciplinas() {
        clnNomeDisciplina.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(cell.getValue().getTitulo()));

        clnCargaHoraria.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(cell.getValue().getCargaHoraria() + "h"));

        clnCurso.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(cell.getValue().getEmailProfessor()));

        tblDisciplinas.setItems(obsDisciplinas);
    }

    private void carregarDatas() {
        List<DiaModel> dias = diaDAO.listar();
        List<DiaModel> diasComTitulo = new ArrayList<>();

        for(DiaModel dia: dias){
            if (dia.getTitulo() != null){
                diasComTitulo.add(dia);
            }
        }

        obsDatas.setAll(diasComTitulo);
        atualizarContadorDatas();
    }

    private void carregarDisciplinas() {
        List<MateriaModel> materias = materiaDAO.listar();
        obsDisciplinas.setAll(materias);
        atualizarContadorDisciplinas();
    }

    private void atualizarContadorDatas() {
        int total = obsDatas.size();
        if (txtContadorDatas != null) txtContadorDatas.setText("Datas Cadastradas (" + total + ")");
        if (txtTotalDatas != null) txtTotalDatas.setText(String.valueOf(total));
    }

    private void atualizarContadorDisciplinas() {
        int total = obsDisciplinas.size();
        if (txtContadorDisciplinas != null) txtContadorDisciplinas.setText("Disciplinas Cadastradas (" + total + ")");
        if (txtTotalDisciplinas != null) txtTotalDisciplinas.setText(String.valueOf(total));
    }

    @FXML
    void adicionarDataFeriado(ActionEvent event) {
        LocalDate data = dpFeriados.getValue();
        String titulo = txtTituloData.getText();
        String descricao = txtDescricaoData.getText();

        if (data == null) {
            new Alert(Alert.AlertType.WARNING, "Selecione uma data.", ButtonType.OK).show();
            return;
        }
        if (titulo == null || titulo.isBlank()) {
            new Alert(Alert.AlertType.WARNING, "Preencha o título.", ButtonType.OK).show();
            return;
        }

        DiaModel dia = new DiaModel(data, false, titulo, descricao);
        cadastroDAO.salvarDia(dia);

        dpFeriados.setValue(null);
        txtTituloData.clear();
        txtDescricaoData.clear();

        carregarDatas();
        new Alert(Alert.AlertType.CONFIRMATION, "Data cadastrada com sucesso!", ButtonType.OK).show();
    }

    @FXML
    void adicionarPeriodo(ActionEvent event) {
        LocalDate dataInicial = dpDataInicial.getValue();
        LocalDate dataFinal = dpDataFinal.getValue();

        if (dataInicial == null || dataFinal == null) {
            new Alert(Alert.AlertType.WARNING, "Preencha as datas de início e fim.", ButtonType.OK).show();
            return;
        }
        if (dataInicial.isAfter(dataFinal)) {
            new Alert(Alert.AlertType.WARNING, "A data inicial não pode ser depois da data final.", ButtonType.OK).show();
            return;
        }

        SemestreService semestreService = new SemestreService();
        SemestreModel novoSemestre = new SemestreModel(dataInicial, dataFinal);
        semestreService.setSemestre(novoSemestre);
        semestreService.criarSemestreComDias();

        dpDataInicial.setValue(null);
        dpDataFinal.setValue(null);
        new Alert(Alert.AlertType.CONFIRMATION, "Período letivo de " + dataInicial.format(fmt) + " até " + dataFinal.format(fmt) + " configurado!", ButtonType.OK).show();
    }

    @FXML
    void adicionarDataSprint(ActionEvent event) {
        LocalDate dataInicial = dpDataInicialSprint.getValue();
        LocalDate dataFinal = dpDataFinalSprint.getValue();

        if (dataInicial == null || dataFinal == null) {
            new Alert(Alert.AlertType.WARNING, "Preencha as datas de início e fim da Sprint.", ButtonType.OK).show();
            return;
        }
        if (dataInicial.isAfter(dataFinal)) {
            new Alert(Alert.AlertType.WARNING, "A data inicial não pode ser depois da data final.", ButtonType.OK).show();
            return;
        }
        if (ChronoUnit.DAYS.between(dataInicial, dataFinal) > 6) {
            new Alert(Alert.AlertType.WARNING, "O período de Sprint pode ter no máximo 7 dias.", ButtonType.OK).show();
            return;
        }

        for (LocalDate d = dataInicial; !d.isAfter(dataFinal); d = d.plusDays(1)) {
            DiaModel dia = new DiaModel(d, false, "Período de Sprint", dataInicial.format(fmt) + " a " + dataFinal.format(fmt));
            cadastroDAO.salvarDia(dia);
        }

        dpDataInicialSprint.setValue(null);
        dpDataFinalSprint.setValue(null);
        carregarDatas();
        new Alert(Alert.AlertType.CONFIRMATION, "Período de Sprint bloqueado: " + dataInicial.format(fmt) + " até " + dataFinal.format(fmt), ButtonType.OK).show();
    }

    @FXML
    void cadastrarDisciplina(ActionEvent event) {
        if (txtIdDisciplina.getText().isBlank() || txtNomeDisciplina.getText().isBlank()) {
            new Alert(Alert.AlertType.WARNING, "Preencha todos os campos.", ButtonType.OK).show();
            return;
        }
        if (!rb40Horas.isSelected() && !rb80Horas.isSelected()) {
            txtInfo.setText("Selecione a carga horária.");
            return;
        }
        if (cmbProfessor.getSelectionModel().getSelectedItem() == null) {
            new Alert(Alert.AlertType.WARNING, "Selecione um professor.", ButtonType.OK).show();
            return;
        }

        int cargaHoraria = rb40Horas.isSelected() ? 40 : 80;

        MateriaModel novaMateria = new MateriaModel(
                txtIdDisciplina.getText(),
                txtNomeDisciplina.getText(),
                cargaHoraria,
                cmbProfessor.getSelectionModel().getSelectedItem().getEmail()
        );

        materiaDAO.salvar(novaMateria);
        txtIdDisciplina.clear();
        txtNomeDisciplina.clear();
        carregarDisciplinas();
        new Alert(Alert.AlertType.CONFIRMATION, "Disciplina cadastrada com sucesso!", ButtonType.OK).show();
    }

    @FXML
    void handleFiltrarDisciplinas(KeyEvent event) {
        String filtro = txtFiltroDisciplina.getText().toLowerCase();
        FilteredList<MateriaModel> filtrada = new FilteredList<>(obsDisciplinas, m ->
                filtro.isBlank() ||
                        m.getTitulo().toLowerCase().contains(filtro) ||
                        m.getSigla().toLowerCase().contains(filtro)
        );
        tblDisciplinas.setItems(filtrada);
    }

    @FXML
    void handleLogout(ActionEvent event) {
        SessaoUsuario.getSessao().limparSessao();
    }

    @FXML
    void handleCancelarNovaLegenda(ActionEvent event) {}

    @FXML
    void handleSalvarNovaLegenda(ActionEvent event) {}

    private void carregarProfessores() {
        List<ProfessorModel> professores = cadastroDAO.listarProfessores();

        cmbProfessor.setConverter(new javafx.util.StringConverter<ProfessorModel>() {
            @Override public String toString(ProfessorModel p) { return p == null ? "" : p.getNome(); }
            @Override public ProfessorModel fromString(String s) { return null; }
        });

        cmbProfessor.setItems(FXCollections.observableArrayList(professores));
    }
}