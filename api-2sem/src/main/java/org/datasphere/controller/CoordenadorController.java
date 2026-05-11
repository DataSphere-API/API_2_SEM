package org.datasphere.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CoordenadorController {

    @FXML
    private Button btAdicionarData;

    @FXML
    private Button btAdicionarLegenda;

    @FXML
    private Button btSair;

    @FXML
    private Button btnCadastrarDisciplina;

    @FXML
    private DatePicker calendario;

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
    private HBox hbBotoesDisciplina;

    @FXML
    private ImageView ivLogo;

    @FXML
    private Tab tabCalendario;

    @FXML
    private Tab tabDisciplinas;

    @FXML
    private TableView<?> tblDatasImportantes;

    @FXML
    private TableView<?> tblDisciplinas;

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
    private VBox vbListaLegendas;

    @FXML
    private VBox vbNovaLegenda;

    @FXML
    private VBox vbSelectedDates;

    @FXML
    void AdicionarData(ActionEvent event) {

    }

    @FXML
    void AdicionarLegenda(ActionEvent event) {

    }

    @FXML
    void CadastrarDisciplina(ActionEvent event) {

    }

    @FXML
    void handleCancelarNovaLegenda(ActionEvent event) {

    }

    @FXML
    void handleFiltrarDisciplinas(KeyEvent event) {

    }

    @FXML
    void handleLogout(ActionEvent event) {

    }

    @FXML
    void handleSalvarNovaLegenda(ActionEvent event) {

    }

}
