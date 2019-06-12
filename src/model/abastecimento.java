package model;


import javafx.scene.control.SortEvent;
import javafx.scene.control.TableView;
import view.AbastecimentoController;

public class abastecimento {
    String fuelType, value, liters, employee;

    public abastecimento(String fuelType, String value, String liters, String employee){
        this.fuelType = fuelType;
        this.value = value;
        this.liters = liters;
        this.employee = employee;
    }

    public String getFuelType(){
        return fuelType;
    }
    public void setFuelType(String fuelType){
        this.fuelType = fuelType;
    }

    public String getValue(){
        return value;
    }
    public void setValue(String value){
        this.value = value;
    }

    public String getLiters(){
        return liters;
    }
    public void setLiters(String liters){
        this.liters = liters;
    }

    public String getEmployee(){
        return employee;
    }
    public void setEmployee(String employee){
        this.employee = employee;
    }

}
