package org.datasphere.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.datasphere.model.SessaoUsuario;
import org.datasphere.service.SemestreService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
    private RadioButton rb40hrs;

    @FXML
    private RadioButton rb80hrs;

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

    @FXML
    void adicionarDataFeriado(ActionEvent event) {

    }

    @FXML
    void adicionarPeriodo(ActionEvent event) {

    }

    @FXML
    void cadastrarDisciplina(ActionEvent event) {

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
    private ComboBox<?> cmbProfessor;


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
            System.out.println("Erro: Preencha as datas de início e fim da Sprint!");
            return;
        }

        if (dataInicial.isAfter(dataFinal)) {
            System.out.println("Erro: A data inicial não pode ser depois da data final!");
            return;
        }

        long diasDeSprint = ChronoUnit.DAYS.between(dataInicial, dataFinal);
        if (diasDeSprint > 6) {
            System.out.println("Erro: O período de Sprint pode ter no máximo 7 dias!");
            return;
        }

        SemestreService semestreService = new SemestreService();

        semestreService.criarSemanaSprint(dataInicial, dataFinal);
        System.out.println("Período de Sprint adicionado e bloqueado para provas: " + dataInicial + " até " + dataFinal);

        dpDataInicialSprint.setValue(null);
        dpDataFinalSprint.setValue(null);
    }
}
