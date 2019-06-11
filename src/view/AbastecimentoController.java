package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.connection;
import utils.messages;

import java.sql.*;


public class AbastecimentoController extends connection {
    @FXML
    private javafx.scene.control.Button closeApp;
    @FXML
    private javafx.scene.control.TextField txtLitersValue;
    @FXML
    private javafx.scene.control.TextField txtSupplyValue;
    @FXML
    javafx.scene.control.Button btnCancelSupply;
    @FXML
    javafx.scene.control.Button btnSaveSupply;
    @FXML
    private javafx.scene.control.SplitMenuButton ddlPaymentType;
    @FXML
    private  javafx.scene.control.SplitMenuButton ddlFuelType;
    @FXML
    private  javafx.scene.control.RadioButton btnBarValues0;
    @FXML
    private  javafx.scene.control.RadioButton btnBarValues1;
    @FXML
    private  javafx.scene.control.RadioButton btnBarValues2;
    @FXML
    private  javafx.scene.control.RadioButton btnBarValues3;

    Stage dialogStage = new Stage();
    Scene scene;

    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    //Super
    public AbastecimentoController(){
        conn =  getConnection();
    }

    @FXML
    private void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) closeApp.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    private void saveSupplyAction(ActionEvent event){


    }

    @FXML
    private void cancelSupplyAction(ActionEvent event){

    }
}

