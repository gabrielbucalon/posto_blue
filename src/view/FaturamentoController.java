package view;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utils.connection;
import utils.messages;
import javafx.collections.FXCollections;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import model.faturamento;

public class FaturamentoController extends connection implements Initializable {

    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public FaturamentoController(){
        conn =  getConnection();
    }

    @FXML
    private javafx.scene.control.Label faturamentoTotal;

    @FXML
    private javafx.scene.control.TableView<model.faturamento> tableView;

    @FXML
    private javafx.scene.control.TableColumn<model.faturamento, String> colVal;

    @FXML
    private javafx.scene.control.TableColumn<model.faturamento, String> nomeFunc;

    @FXML
    private javafx.scene.control.TableColumn<model.faturamento, String> tipoComb;

    @FXML
    private javafx.scene.control.TableColumn<model.faturamento, String> qtd;

    public void initialize(URL location, ResourceBundle resources) {
        String _sql = "SELECT * FROM total_faturamento_vw";
        String _sql2 = "select * from table_faturamento_vw ";

        ObservableList<faturamento>obList = FXCollections.observableArrayList();

        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(_sql);
            while(rs.next()){
                double val = rs.getDouble("teste");
                faturamentoTotal.setText("R$" + val);
                System.out.println(val);
            }
           // st.close();

        }catch (Exception err){
            messages.infoBoxErr("erro ao pegar valor", "ERRO!", null);
            System.out.println("Ops, não deu para pegar o valor" + err);
        }

        //lista de vendas
        try{
            Statement st2 = conn.createStatement();
            ResultSet rs2 = st2.executeQuery(_sql2);

            while(rs2.next()){
                    obList.add(new faturamento(rs2.getString("valor"), rs2.getString("nomeCompleto"),
                        rs2.getString("tipo"), rs2.getString("qntdLitros")));

                    System.out.println(rs2.getString("valor"));
            }
           // st2.close();
            //faturamentoTotal.setText(val.toString());
        }catch (Exception err){
            messages.infoBoxErr("erro ao pegar valor", "ERRO!", null);
            System.out.println("Ops, não deu para pegar o valor" + err);
        }

        colVal.setCellValueFactory(new PropertyValueFactory<>("val"));
        nomeFunc.setCellValueFactory(new PropertyValueFactory<>("nomeFun"));
        tipoComb.setCellValueFactory(new PropertyValueFactory<>("type"));
        qtd.setCellValueFactory(new PropertyValueFactory<>("qntd"));

        tableView.setItems(obList);

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
}
