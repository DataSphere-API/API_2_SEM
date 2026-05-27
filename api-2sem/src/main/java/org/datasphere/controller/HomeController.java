package org.datasphere.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
    private ToggleGroup grupoBotao;

    @FXML
    private void initialize() {
        grupoBotao = new ToggleGroup();
        rbCoordenador.setToggleGroup(grupoBotao);
        rbProfessor.setToggleGroup(grupoBotao);
    }


    @FXML
    void entrar(ActionEvent event) {
        
    }

}
