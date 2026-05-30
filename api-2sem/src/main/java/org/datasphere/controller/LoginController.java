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
import org.datasphere.model.ProfessorModel;
import org.datasphere.model.SessaoUsuario;
import org.datasphere.service.LoginService;

import java.io.IOException;

public class LoginController {

    @FXML
    private ImageView ivLogo;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Button btnLogin;

    @FXML
    private Hyperlink linkCriarConta;

    private final LoginService loginService = new LoginService();

    @FXML
    private void entrar(ActionEvent event) {
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        boolean loginSucesso = loginService.realizarLogin(email, senha);

        if (loginSucesso) {
            ProfessorModel profLogado = SessaoUsuario.getSessao().getProfessorLogado();
             profLogado.getNome();


            if (profLogado.isCoordenador()) {
                mudarTela(event, "/org/datasphere/home.fxml", "SIGA.ME - Home");
            } else {
                mudarTela(event, "/org/datasphere/cadastro-aula.fxml", "SIGA.ME - Planejamento de Aulas");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Nome de usuário ou senha incorretos, tente novamente!", ButtonType.OK);
            alert.show();
            txtSenha.clear();
        }
    }

    @FXML
    private void criarConta(ActionEvent event) {

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
}