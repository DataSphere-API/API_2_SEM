package org.datasphere.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.datasphere.dao.CadastroDAO;
import org.datasphere.dao.interfaces.IDAO;
import org.datasphere.model.ProfessorModel;

import java.io.IOException;


public class CadastroUsuaController {

    @FXML
    private Button btCadastroUsua;

    @FXML
    private ImageView ivLogo;

    @FXML
    private Hyperlink linkLogin;

    @FXML
    private PasswordField txtConfirmarSenha;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private PasswordField txtSenha;

    private CadastroDAO cadastroDAO = new CadastroDAO();

    @FXML
    void voltarLogin(ActionEvent event) {
        mudarTela(event,"/org/datasphere/login.fxml", "SIGA.ME");
    }

    private void mudarTela(ActionEvent event, String caminhoFxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFxml));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cadastrarUsua(ActionEvent event) {
        if (txtNome.getText().isBlank() || txtEmail.getText().isBlank() || txtSenha.getText().isBlank()) {
            new Alert(Alert.AlertType.ERROR, "Preencha todos os campos.", ButtonType.OK).show();
            return;
        }
        if (!txtSenha.getText().equals(txtConfirmarSenha.getText())) {
            new Alert(Alert.AlertType.ERROR, "As senhas não são iguais.", ButtonType.OK).show();
            txtSenha.clear();
            txtConfirmarSenha.clear();
            return;
        }

        ProfessorModel professorCadastrado = new ProfessorModel();
        professorCadastrado.setNome(txtNome.getText());
        professorCadastrado.setEmail(txtEmail.getText());
        professorCadastrado.setSenha(txtSenha.getText());

        cadastroDAO.cadastrar(professorCadastrado);
        new Alert(Alert.AlertType.CONFIRMATION, "Cadastro concluído com sucesso!", ButtonType.OK).show();
        mudarTela(event, "/org/datasphere/login.fxml", "SIGA.ME");
    }


}
