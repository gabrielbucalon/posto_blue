package utils;

//Libs
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class connection extends BDConnection {
    //SUPER
    public connection(){
        this.driver = "com.mysql.cj.jdbc.Driver";
        this.porta = 3306;
        this.servidor = "localhost";
        this.bd = "postoblue";
        this.usuario = "root";
        this.senha = "pa24140123";
    }

    public Connection getConnection(){ // metodo para conexão com banco de dados
        try { // Tentativa de conectar
            Class.forName(driver);
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = DriverManager.getConnection(getURL(), usuario, senha);
            System.out.println("Conetado com sucesso!");
        } catch (SQLException ex) { // Se não conseguir conectar mostrara o motivo do erro da conexeção
            Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERRO AO CARREGAR DRIVE");
        } catch (ClassNotFoundException ex) { // Caso aconteça algum problema no driver
            Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;
    }

    @Override
    public  String getURL() { // metodo que retorna url da conexão
        return "jdbc:mysql://" + this.servidor + ":" + this.porta + "/" + this.bd
                + "?useTimezone=true&serverTimezone=UTC";
    }
}
