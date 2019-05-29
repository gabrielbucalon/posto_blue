package view;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import utils.connection;


public class LoginController {
    @FXML
    private javafx.scene.control.Button closeApp;

    //Super
    public LoginController(){
        connection.getConnection();
    }

    @FXML
    private void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) closeApp.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    private void login(){

    }
}
