package org.datasphere.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private Button btnLogin;

    @FXML
    private ImageView ivLogo;

    @FXML
    private RadioButton rbCoordenador;

    @FXML
    private RadioButton rbProfessor;

    @FXML
    void entrar(ActionEvent event) {
        if (rbProfessor.isSelected()) {
            carregarTela(event, "/org/datasphere/cadastro-aula.fxml", "SIGA.ME - Planejamento de Aulas");

        } else if (rbCoordenador.isSelected()) {
            carregarTela(event, "/org/datasphere/coordenador.fxml", "SIGA.ME - Área do Coordenador");
        }
    }

    private void carregarTela(ActionEvent event, String caminhoFxml, String titulo) {
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