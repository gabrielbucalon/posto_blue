package model;

import javafx.fxml.FXML;

public class Tanque {
    private double quantidadeCombustivel;
    private String tipoCombustivel;

    @FXML
    public void initialize(){ // Segundo a ser carregado

    }

    public String getTipoCombustivel() { // Pega o que foi digitado
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public double getQuantidadeCombustivel() {
        return quantidadeCombustivel;
    }

    public void setQuantidadeCombustivel(double quantidadeCombustivel) {
        this.quantidadeCombustivel = quantidadeCombustivel;
    }


}