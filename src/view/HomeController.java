package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class HomeController {
    @FXML
    private javafx.scene.control.Button btnAbastecer;
    @FXML
    private javafx.scene.control.Button btnFaturamento;
    @FXML
    private javafx.scene.control.Button btnTanques;
    @FXML
    private javafx.scene.control.Button btnSobre;
    @FXML
    private javafx.scene.control.Button btnLogoff;
    @FXML
    private javafx.scene.control.Label lblAbastecer;
    @FXML
    private javafx.scene.control.Label lblFaturamento;
    @FXML
    private javafx.scene.control.Label lblTanques;
    @FXML
    private javafx.scene.control.Label lblSobre;
    @FXML
    private javafx.scene.control.Label lblSair;

    Stage dialogStage = new Stage();
    Scene scene;

    @FXML // Quando o mouse passar por esse botão ele deixará com essas classes
    //Btn Abastecer ao entrar
    private void moveFuelEntry(MouseEvent eventMouse){
        btnAbastecer.setStyle("-fx-background-color: #5d8aa8;");
        lblAbastecer.setTextFill (Color.web ("#FFF"));
        lblAbastecer.setStyle("-fx-font-weight: bold");
    }

    @FXML // Quando o mouse passar por esse botão ele deixará com essas classes
    //Btn Abastecer ao sair
    private void moveFuelExit(MouseEvent eventMouse){
        btnAbastecer.setStyle("-fx-background-color: #ffffff; -fx-border-color: #5a849f; -fx-border-radius: 2px;");
        lblAbastecer.setTextFill (Color.web ("#000"));
        lblAbastecer.setStyle("-fx-font-weight: normal");
    }

    @FXML // Quando o mouse passar por esse botão ele deixará com essas classes
    //Btn faturamento ao entrar
    private void moveEntryFaturamento(MouseEvent eventMouse){
        btnFaturamento.setStyle("-fx-background-color: #5d8aa8;");
        lblFaturamento.setTextFill (Color.web ("#FFF"));
        lblFaturamento.setStyle("-fx-font-weight: bold");
    }

    @FXML // Quando o mouse passar por esse botão ele deixará com essas classes
    //Btn faturamento ao sair
    private void moveExitFaturamento(MouseEvent eventMouse){
        btnFaturamento.setStyle("-fx-background-color: #ffffff; -fx-border-color: #5a849f; -fx-border-radius: 2px;");
        lblFaturamento.setTextFill (Color.web ("#000"));
        lblFaturamento.setStyle("-fx-font-weight: normal");
    }

    @FXML // Quando o mouse passar por esse botão ele deixará com essas classes
    //Btn Tanque ao entrar
    private void moveEntryTank(MouseEvent eventMouse){
        btnTanques.setStyle("-fx-background-color: #5d8aa8;");
        lblTanques.setTextFill (Color.web ("#FFF"));
        lblTanques.setStyle("-fx-font-weight: bold");
    }

    @FXML // Quando o mouse passar por esse botão ele deixará com essas classes
    //Btn Tanque ao sair
    private void moveExitTank(MouseEvent eventMouse){
        btnTanques.setStyle("-fx-background-color: #ffffff; -fx-border-color: #5a849f; -fx-border-radius: 2px;");
        lblTanques.setTextFill (Color.web ("#000"));
        lblTanques.setStyle("-fx-font-weight: normal");
    }


    @FXML // Quando o mouse passar por esse botão ele deixará com essas classes
    //Btn sobre ao entrar
    private void moveInfoEntry(MouseEvent eventMouse){
        btnSobre.setStyle("-fx-background-color: #5d8aa8;");
        lblSobre.setTextFill (Color.web ("#FFF"));
        lblSobre.setStyle("-fx-font-weight: bold");
    }

    @FXML // Quando o mouse passar por esse botão ele deixará com essas classes
    //Btn sobre ao sair
    private void moveInfoExit(MouseEvent eventMouse){
        btnSobre.setStyle("-fx-background-color: #ffffff; -fx-border-color: #5a849f; -fx-border-radius: 2px;");
        lblSobre.setTextFill (Color.web ("#000"));
        lblSobre.setStyle("-fx-font-weight: normal");
    }

    @FXML // Quando o mouse passar por esse botão ele deixará com essas classes
    //Btn logoff ao entrar
    private void moveLogoffEntry(MouseEvent eventMouse){
        btnLogoff.setStyle("-fx-background-color: #5d8aa8;");
        lblSair.setTextFill (Color.web ("#FFF"));
        lblSair.setStyle("-fx-font-weight: bold");
    }

    @FXML // Quando o mouse passar por esse botão ele deixará com essas classes
    //Btn logoff ao sair
    private void moveLogoffExit(MouseEvent eventMouse){
        btnLogoff.setStyle("-fx-background-color: #ffffff; -fx-border-color: #5a849f; -fx-border-radius: 2px;");
        lblSair.setTextFill (Color.web ("#000"));
        lblSair.setStyle("-fx-font-weight: normal");
    }


    @FXML // Se o usuário queria sair
    private void actionLogoff(ActionEvent event) throws IOException {
        ButtonType logoff = new ButtonType("Sair", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Tem certeza que deseja sair? "
                        + " Você voltará para para tela de login",
                logoff,
                cancel);

        alert.setTitle("Sair");
        Optional<ButtonType> result = alert.showAndWait();

        if (((Optional) result).orElse(cancel) == logoff) {
            Node source = (Node) event.getSource(); // Pega o evento do botão
            dialogStage = (Stage) source.getScene().getWindow();
            dialogStage.close();
            scene = new Scene(FXMLLoader.load(getClass().getResource("login.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
    }

    @FXML // Ao clicar ele mandara o usuario para tela de abastacer
    private void actionAbastecer(ActionEvent event){
        System.out.println("Abastecer");
    }

    @FXML // Ao clicar ele mandara o usuario para tela de faturamento
    private void actionFaturamento(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource(); // Pega o evento do botão
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("faturamento.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @FXML
    private void actionTanques(ActionEvent event){
        System.out.println("action tanques");
    }

    @FXML
    private void actionSobre(ActionEvent event){
        System.out.println("Action sobre");
    }
}
