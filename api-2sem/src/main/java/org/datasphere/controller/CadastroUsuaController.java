package org.datasphere.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;


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

    }


}
