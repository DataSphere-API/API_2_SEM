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

    @FXML
    void Login(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/datasphere/login.fxml"));


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

    @FXML
    void cadastrarUsua(ActionEvent event) {

    }
}
