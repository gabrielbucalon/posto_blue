package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.abastecimento;
import utils.BDConnection;
import utils.connection;
import utils.messages;

import javax.naming.spi.DirStateFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


public class AbastecimentoController extends connection implements Initializable {
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
    @FXML
    private javafx.scene.control.TableView<model.abastecimento> tblRegister;
    @FXML
    private javafx.scene.control.TableColumn<model.abastecimento, String> clnFuelType;
    @FXML
    private javafx.scene.control.TableColumn<model.abastecimento, String> clnValue;
    @FXML
    private javafx.scene.control.TableColumn<model.abastecimento, String> clnLiters;
    @FXML
    private javafx.scene.control.TableColumn<model.abastecimento, String> clnEmployee;

    Stage dialogStage = new Stage();
    Scene scene;

    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    //Super
    public AbastecimentoController(){
        conn =  getConnection();
    }


    ObservableList<abastecimento> oblist = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources){

        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM table_abastecimento_vw");

            while (rs.next()){
                oblist.add(new abastecimento(rs.getString("tipo"), rs.getString("valor"),
                        rs.getString("qntdLitros"), rs.getString("nomeCompleto")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        clnFuelType.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
        clnValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        clnLiters.setCellValueFactory(new PropertyValueFactory<>("liters"));
        clnEmployee.setCellValueFactory(new PropertyValueFactory<>("employee"));

        tblRegister.setItems(oblist);
    }

    @FXML
    private void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) closeApp.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    @FXML
    private void actionBack(ActionEvent event) throws IOException {
        Stage dialogStage = new Stage();
        Scene scene;

        Node source = (Node) event.getSource(); // Pega o evento do botão
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("home.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
    }
    @FXML
    private void saveSupplyAction(ActionEvent event){


    }

    @FXML
    private void cancelSupplyAction(ActionEvent event){

    }

}

