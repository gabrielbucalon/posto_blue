package model;

import java.io.IOException;
import java.sql.Connection;

import utils.connection;

public class faturamento extends connection {

    String val;
    String nomeFun;
    String type;
    String qntd;

    //valor
    public String getVal(){
        return val;
    }
    public void setVal(String val){
        this.val = val;
    }

    //nome funcionario
    public String getNomeFunc(){
        return val;
    }
    public void setNomeFunc(String nomeFunc){
        this.nomeFun = nomeFunc;
    }

    //tipo
    public String getTipo(){
        return type;
    }
    public void setTipo(String tipo){
        this.type = tipo;
    }

    //quantidade
    public String getQuantidade(){
        return qntd;
    }
    public void setQuantidade(String qtd){
        this.qntd = qtd;
    }


    Connection conn = null;
    public faturamento(String valor, String nomeCompleto, String tipo, String qntdLitros){conn =  getConnection();}

    public void getFaturamento(){
        getConnection();
    }
}
