package org.datasphere.controller;

import javafx.collections.FXCollections;
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
import org.datasphere.model.MateriaModel;
import org.datasphere.model.ProfessorModel;
import org.datasphere.model.SessaoUsuario;
import org.datasphere.service.SemestreService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.util.List;

public class CoordenadorController {

    @FXML
    private Button btAdicionarData;

    @FXML
    private Button btAdicionarPeriodo;

    @FXML
    private Button btSair;

    @FXML
    private Button btnCadastrarDisciplina;

    @FXML
    private TableColumn<?, ?> clnCargaHoraria;

    @FXML
    private TableColumn<?, ?> clnCurso;

    @FXML
    private TableColumn<?, ?> clnData;

    @FXML
    private TableColumn<?, ?> clnDescricao;

    @FXML
    private TableColumn<?, ?> clnNomeDisciplina;

    @FXML
    private TableColumn<?, ?> clnTitulo;

    @FXML
    private ColorPicker cpNovaLegendaCor;

    @FXML
    private DatePicker dpDataFinal;

    @FXML
    private DatePicker dpDataInicial;

    @FXML
    private DatePicker dpFeriados;

    @FXML
    private HBox hbBotoesDisciplina;

    @FXML
    private ImageView ivLogo;

    @FXML
    private RadioButton rb40Horas;

    @FXML
    private RadioButton rb80Horas;

    @FXML
    private Tab tabCalendario;

    @FXML
    private Tab tabDisciplinas;

    @FXML
    private TableView<?> tblDatasImportantes;

    @FXML
    private TableView<?> tblDisciplinas;

    @FXML
    private Text txtContadorDatas;

    @FXML
    private Text txtContadorDisciplinas;

    @FXML
    private TextField txtCurso;

    @FXML
    private TextArea txtDescricaoData;

    @FXML
    private TextField txtFiltroDisciplina;

    @FXML
    private Text txtInfo;

    @FXML
    private TextField txtNomeDisciplina;

    @FXML
    private TextField txtNovaLegendaNome;

    @FXML
    private TextField txtTituloData;

    @FXML
    private Text txtTituloDisciplina;

    @FXML
    private Text txtTotalDatas;

    @FXML
    private Text txtTotalDisciplinas;

    @FXML
    private VBox vbNovaLegenda;

    private CadastroDAO cadastroDAO = new CadastroDAO();

    private MateriaDAO materiaDAO = new MateriaDAO();

    public void initialize() {
        carregarProfessores();
    }

    @FXML
    void adicionarDataFeriado(ActionEvent event) {

    }

    @FXML
    void adicionarPeriodo(ActionEvent event) {

    }

    @FXML
    void cadastrarDisciplina(ActionEvent event) {
        if (txtIdDisciplina.getText().isEmpty() || txtNomeDisciplina.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Preencha todos os campos.", ButtonType.OK);
            alert.show();
            return;
        }
        if (!rb40Horas.isSelected() && !rb80Horas.isSelected()) {
            txtInfo.setText("Selecione a carga horária.");
            return;
        }
        if (cmbProfessor.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Selecione um professor.", ButtonType.OK);
            alert.show();
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Disciplina cadastrada com sucesso!", ButtonType.OK);
        alert.show();
        txtIdDisciplina.clear();
        txtNomeDisciplina.clear();
    }

    private void carregarProfessores() {
        List<ProfessorModel> professores = cadastroDAO.listarProfessores();

        cmbProfessor.setConverter(new javafx.util.StringConverter<ProfessorModel>() {
            @Override
            public String toString(ProfessorModel p) {
                if (p == null) return "";
                return p.getNome();
            }
            @Override
            public ProfessorModel fromString(String s) { return null; }
        });

        cmbProfessor.setItems(FXCollections.observableArrayList(professores));
    }

    @FXML
    void handleCancelarNovaLegenda(ActionEvent event) {

    }

    @FXML
    void handleFiltrarDisciplinas(KeyEvent event) {

    }

    @FXML
    void handleLogout(ActionEvent event) {
        SessaoUsuario.getSessao().limparSessao();

    }

    @FXML
    void handleSalvarNovaLegenda(ActionEvent event) {

    }

    @FXML
    private ToggleGroup grupoHorario;

    @FXML
    private TextField txtIdDisciplina;

    @FXML
    private ComboBox<ProfessorModel> cmbProfessor;


    @FXML
    private DatePicker dpDataInicialSprint;

    @FXML
    private DatePicker dpDataFinalSprint;


    @FXML
    private Button btAdicionarPeriodoSprint;

    @FXML
    void adicionarDataSprint(ActionEvent event) {

        LocalDate dataInicial = dpDataInicialSprint.getValue();
        LocalDate dataFinal = dpDataFinalSprint.getValue();

        if (dataInicial == null || dataFinal == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Erro: Preencha as datas de início e fim da Sprint!", ButtonType.OK);
            alert.show();
            return;
        }

        if (dataInicial.isAfter(dataFinal)) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Erro: A data inicial não pode ser depois da data final!", ButtonType.OK);
            alert.show();
            return;
        }

        long diasDeSprint = ChronoUnit.DAYS.between(dataInicial, dataFinal);
        if (diasDeSprint > 6) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Erro: O período de Sprint pode ter no máximo 7 dias!", ButtonType.OK);
            alert.show();
            return;
        }

        SemestreService semestreService = new SemestreService();

        semestreService.criarSemanaSprint(dataInicial, dataFinal);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Período de Sprint adicionado e bloqueado para provas: " + dataInicial + " até " + dataFinal, ButtonType.OK);
        alert.show();

        dpDataInicialSprint.setValue(null);
        dpDataFinalSprint.setValue(null);
    }
}
