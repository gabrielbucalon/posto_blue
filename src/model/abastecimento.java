package model;

public class abastecimento {
    String fuelType, value, liters, employee, type;

    public abastecimento(String fuelType, String value, String liters, String employee){
        this.fuelType = fuelType;
        this.value = value;
        this.liters = liters;
        this.employee = employee;
    }
    public abastecimento(String type){
        this.type = type;
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

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    /*@Override
    public String toString() {
        return getFuelType();
    }*/

    @Override
    public String toString() {
        return getType();
    }
}