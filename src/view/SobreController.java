package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SobreController {
    Stage dialogStage = new Stage();
    Scene scene;

    @FXML
    private void actionBackToHome(ActionEvent event) throws IOException { //
        Node source = (Node) event.getSource(); // Pega o evento do botão
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close(); // Fecha a tela atual
        scene = new Scene(FXMLLoader.load(getClass().getResource("home.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show(); // Abre a tela solicitada (Sobre);
    }
}
