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

}
