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


public class LoginController extends connection {
    @FXML
    private javafx.scene.control.Button closeApp;
    @FXML
    private javafx.scene.control.TextField textUser;
    @FXML
    private javafx.scene.control.Label lblRequiredUser;
    @FXML
    javafx.scene.control.Label lblRequiredPassword;
    @FXML
    javafx.scene.control.Label lblUserNotFound;
    @FXML
    private javafx.scene.control.TextField textPassword;
    @FXML
    private  javafx.scene.control.Button login;

    Stage dialogStage = new Stage();
    Scene scene;

    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    //Super
    public LoginController(){
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
    private void loginButtonAction(ActionEvent event){
        //Recebendo o que usuario digitou
        String user = textUser.getText();
        String password = textPassword.getText();
        lblUserNotFound.setVisible(false);
        //Consulta dos funcionarios
        String _sql = "SELECT * FROM funcionario WHERE usuario = ? AND senha = ?";

        if(textUser.getText().equals("") && textPassword.getText().equals("")){
            lblRequiredUser.setVisible(true); // Caso ele não digite os dois, mostrara as duas labels
            lblRequiredPassword.setVisible(true);
        }else if(textPassword.getText().equals("")){
            lblRequiredPassword.setVisible(true); // Caso usuário não digitar a senha, mostrara essa label
        }else if(textUser.getText().equals("")){
            System.out.println("entrou nos dois nulos");
            lblRequiredUser.setVisible(true); // Mesma coisa na condição do usuário
        }else{
            // Label's fica invisível
            lblRequiredUser.setVisible(false);
            lblRequiredPassword.setVisible(false);
            try{
                preparedStatement = con.prepareStatement(_sql);
                preparedStatement.setString(1, user);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                //Caso ele não encontre o usuario, entrara nessa condição
                if(!resultSet.next()){
                    lblUserNotFound.setVisible(true);
                }else {
                    Node source = (Node) event.getSource(); // Pega o evento do botão
                    dialogStage = (Stage) source.getScene().getWindow();
                    dialogStage.close();
                    scene = new Scene(FXMLLoader.load(getClass().getResource("home.fxml")));
                    dialogStage.setScene(scene);
                    dialogStage.show();
                }
            }catch (Exception err){

                messages.infoBoxErr("Não foi possível entrar\nTente novamente", "ERRO!", null);
                System.out.println("Ops, não deu para logar " + err);
            }
        }
    }
}
