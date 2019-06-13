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
import java.util.ArrayList;
import java.util.List;
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
    private javafx.scene.control.ComboBox ddlPaymentType;
    @FXML
    private  javafx.scene.control.ComboBox ddlFuelType;
    @FXML
    private javafx.scene.control.ButtonBar btnBarValues;
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
    ObservableList<abastecimento> oblist = FXCollections.observableArrayList();
    ObservableList<abastecimento> fuelTypeList = FXCollections.observableArrayList();
    ObservableList<abastecimento> paymentTypeList = FXCollections.observableArrayList();
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    //Super
    public AbastecimentoController(){
        conn =  getConnection();
    }


    public void initialize(URL location, ResourceBundle resources){

        loadFuelType();
        loadPaymentType();
        selectTable();

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
        try {
            insertSupply();
            messages.infoBox("", "Abastecer", "Abastecimento realizado!");
            txtLitersValue.clear();
            txtSupplyValue.clear();
            ddlFuelType.setValue(0);
            selectTable();

        } catch(Exception err){
            messages.infoBoxErr("", "ERRO", "Erro ao realizar abastecimento");
        }
    }

    @FXML
    private void cancelSupplyAction(ActionEvent event){

    }

    public void loadFuelType(){
        abastecimento comb1 = new abastecimento("Diesel");
        abastecimento comb2 = new abastecimento("Etanol");
        abastecimento comb3 = new abastecimento("Gasolina");

        fuelTypeList.add(comb1);
        fuelTypeList.add(comb2);
        fuelTypeList.add(comb3);

        ddlFuelType.setItems(fuelTypeList);

    }

    public void loadPaymentType(){
        abastecimento comb1 = new abastecimento("Cartão de Crédito");
        abastecimento comb2 = new abastecimento("Cartão de Débito");
        abastecimento comb3 = new abastecimento("Dinheiro");

        paymentTypeList.add(comb1);
        paymentTypeList.add(comb2);
        paymentTypeList.add(comb3);

        ddlPaymentType.setItems(paymentTypeList);

    }

    public void selectTable(){
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

    public void insertSupply(){
        try{
            //numa futura implementação, associar ao funcionário e clientes específicos
            CallableStatement cs = conn.prepareCall("SELECT FUNC_INSERE_ABASTECIMENTO(?, ?, ?, ?, ?)");
            if(ddlFuelType.getValue().toString().equals("Diesel")){
                cs.setString("idFuncionario", "2");
                cs.setString("idCliente", "3");
                cs.setString("idBomba", "3");
                cs.setString("valor",txtSupplyValue.getText());
                cs.setString("qntdLitros", txtLitersValue.getText());
                cs.executeUpdate();
            }
            else if(ddlFuelType.getValue().toString().equals("Etanol")){
                cs.setString("idFuncionario", "2");
                cs.setString("idCliente", "3");
                cs.setString("idBomba", "1");
                cs.setString("valor",txtSupplyValue.getText());
                cs.setString("qntdLitros", txtLitersValue.getText());
                cs.executeUpdate();
            }
            else if(ddlFuelType.getValue().toString().equals("Gasolina")){
                cs.setString("idFuncionario", "2");
                cs.setString("idCliente", "3");
                cs.setString("idBomba", "2");
                cs.setString("valor",txtSupplyValue.getText());
                cs.setString("qntdLitros", txtLitersValue.getText());
                cs.executeUpdate();
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void rbClick(){
        if(btnBarValues0.isSelected()){
        }
    }


}

