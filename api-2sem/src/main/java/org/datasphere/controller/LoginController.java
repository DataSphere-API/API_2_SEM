package org.datasphere.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

// 1. IMPORTANTE: Importar a classe LoginDAO que está em outro pacote
import javafx.stage.Stage;
import org.datasphere.model.LoginDAO;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private ImageView ivLogo;

    @FXML
    private Hyperlink linkCriarConta;

    @FXML
    private Hyperlink linkEsqueciSenha;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;


    private final LoginDAO loginDAO = new LoginDAO();

    @FXML
    void criarConta(ActionEvent event) {
        // Lógica de criar conta
    }

    @FXML
    void entrar(ActionEvent event) {
        String email = txtEmail.getText();
        String senha = txtSenha.getText();


        if (email.trim().isEmpty() || senha.trim().isEmpty()) {
            System.out.println("Por favor, preencha todos os campos.");
            return;
        }

        String usuarioLogado = loginDAO.checarLogin(email, senha);
        if ("Login válido".equals(usuarioLogado)) {
            System.out.println("Acesso liberado! Quem está entrando: " +email);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/datasphere/cadastro-aula.fxml"));
                Parent root = loader.load();

                Scene novaCena = new Scene(root);

                Stage stageAtual = (Stage) btnLogin.getScene().getWindow();


                stageAtual.setScene(novaCena);
                stageAtual.show();

            } catch (IOException e) {
                System.err.println("Erro ao carregar o arquivo cadastro-aula.fxml. Verifique se o caminho está correto.");
                e.printStackTrace();
            }

        } else {
            System.out.println("Usuário ou senha incorretos.");

        }
    }
        }



