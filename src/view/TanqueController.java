package view;

import com.jfoenix.controls.JFXSnackbar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Tanque;

import java.io.IOException;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;

import utils.connection;

public class TanqueController extends connection {

    @FXML
    private javafx.scene.control.Button btnInsertCombustivel;
    @FXML
    private javafx.scene.control.Button saveCombustivel;
    @FXML
    private javafx.scene.control.Button btnCancel;
    @FXML
    private javafx.scene.control.Label lblInsert;
    @FXML
    private javafx.scene.control.Label lblCancel;
    @FXML
    private javafx.scene.control.Label lblSave;
    @FXML
    private javafx.scene.control.Label lblRequiredCombustivel;
    @FXML
    private javafx.scene.control.Label lblRequiredQtdLitros;
    @FXML
    private  javafx.scene.layout.Pane paneInsertFullTank;
    @FXML
    private javafx.scene.control.ComboBox<Tanque> typeFullTank;
    private List<Tanque> tanques = new ArrayList<>();
    private ObservableList<Tanque> obsTanques;
    @FXML
    private javafx.scene.control.TextField txtFieldQtdLitros;

    Stage dialogStage = new Stage();
    Scene scene;
    JFXSnackbar toast = new JFXSnackbar();
    public void initialize(){
        System.out.println("initialize tanque");
        loadFullTank();
    }

    public void loadFullTank(){

        //Criações de objs
        Tanque etanol = new Tanque("Etanol");
        Tanque diesel = new Tanque("Diesel");
        Tanque gasolina = new Tanque("Gasolina");

        //Adicionando tipos de combustivel
        tanques.add(etanol);
        tanques.add(diesel);
        tanques.add(gasolina);

        obsTanques = FXCollections.observableArrayList(tanques);
        typeFullTank.setItems(obsTanques); // Coloca na lista os tipos de combustivel
    }

    @FXML
    private void actionBackToHome(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource(); // Pega o evento do botão
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("home.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @FXML
    private void entryMoveInsertFullTank(MouseEvent eventMouse){
        btnInsertCombustivel.setStyle("-fx-background-color: #5d8aa8; -fx-border-radius: 10px;");
        lblInsert.setTextFill (Color.web ("#FFF"));
        lblInsert.setStyle("-fx-font-weight: bold");
    }

    @FXML
    private void ExitMoveInsertFullTank(MouseEvent eventMouse){
        btnInsertCombustivel.setStyle("-fx-background-color: #ffffff; -fx-border-color: #5a849f; -fx-border-radius: 10px;");
        lblInsert.setTextFill (Color.web ("#000"));
        lblInsert.setStyle("-fx-font-weight: normal");
    }

    @FXML
    private void actionInsertFullTank(ActionEvent event){ // Ao clicar o pane irá aparecer
        paneInsertFullTank.setVisible(true);
    }

    @FXML
    private void entryCancel(){
        btnCancel.setStyle("-fx-background-color: #ff0000; -fx-border-radius: 10px;"); // style button
        lblCancel.setTextFill (Color.web ("#fff")); //color white on label
        lblCancel.setStyle("-fx-font-weight: bold"); // font bold on label
    }

    @FXML
    private void exitCancel(){
        btnCancel.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #FF0000; -fx-border-radius: 10px;");
        lblCancel.setTextFill (Color.web ("#000"));
        lblCancel.setStyle("-fx-font-weight: normal");
    }

    @FXML
    private void entrySave(){
        saveCombustivel.setStyle("-fx-background-color: #0087A0; -fx-border-radius: 10px;"); // style button
        lblSave.setTextFill (Color.web ("#fff")); //color white on label
        lblSave.setStyle("-fx-font-weight: bold"); // font bold on label
    }

    @FXML
    private void exitSave(){
        saveCombustivel.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #0087A0; -fx-border-radius: 10px;");
        lblSave.setTextFill (Color.web ("#000"));
        lblSave.setStyle("-fx-font-weight: normal");
    }

    @FXML
    private void getChangeQtdLitros( EventHandler handler  ){
        System.out.println(handler);
    }

    @FXML
    private void ActionExitPane(){
        paneInsertFullTank.setVisible(false);
    }

    @FXML
    private void actionSave(ActionEvent event) {

    if(typeFullTank.getValue() == null && txtFieldQtdLitros.getText().equals("")){
        lblRequiredQtdLitros.setVisible(true);
        lblRequiredCombustivel.setVisible(true);
    }else if(typeFullTank.getValue() == null){
            lblRequiredCombustivel.setVisible(true);
        }else if(txtFieldQtdLitros.getText().equals("")){
            lblRequiredQtdLitros.setVisible(true);
        }else{
            try {
                getConnection();
                CallableStatement cs;
                cs = con.prepareCall("{call PROC_COMPRA_COMBUSTIVEL(?,?)}");
                if(typeFullTank.getValue().toString().equals("Gasolina")){
                    cs.setString("valorAdicional", txtFieldQtdLitros.getText());
                    cs.setString("idTipoTanque", "2");
                    cs.executeUpdate();
                    utils.messages.infoBox("Gasolina salva!", "Salvo!", "Salvo com sucesso");
                }else if(typeFullTank.getValue().toString().equals("Etanol")){
                    cs.setString("valorAdicional", txtFieldQtdLitros.getText());
                    cs.setString("idTipoTanque", "1");
                    cs.executeUpdate();
                    utils.messages.infoBox("Etanol salvo!", "Salvo!", "Salvo com sucesso");
                }else if(typeFullTank.getValue().toString().equals("Diesel")){
                    cs.setString("valorAdicional", txtFieldQtdLitros.getText());
                    cs.setString("idTipoTanque", "3");
                    cs.executeUpdate();
                    utils.messages.infoBox("Diesel salvo!", "Salvo!", "Salvo com sucesso");
                }else{
                    System.out.println("ops, nenhuma opção a certa");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
