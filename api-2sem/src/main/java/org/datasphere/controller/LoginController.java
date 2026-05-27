package org.datasphere.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
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
            String nomeProf = SessaoUsuario.getSessao().getProfessorLogado().getNome();
            System.out.println("Login efetuado com sucesso! Bem-vindo, " + nomeProf);

            irParaTelaPrincipal(event);
        } else {
            System.out.println("Erro: E-mail ou senha incorretos.");
        }
    }

    @FXML
    private void criarConta(ActionEvent event) {
        System.out.println("Redirecionando para a tela de Cadastro de Usuário...");
    }

    private void irParaTelaPrincipal(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/datasphere/cadastro-aula.fxml"));


            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("SIGA.ME - Planejamento de Aulas");
            stage.show();

        } catch (IOException e) {
            System.out.println("Erro ao carregar a tela principal cadastro-aula.fxml!");
            e.printStackTrace();
        }
    }
}