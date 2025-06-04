package org.example.modelo;

public class Privado extends Vehiculo implements IMonitoreo{

    private String empresaPropietaria;

    public Privado (String marca, String placa, String modelo, String empresaPropietaria) {
        super(marca, placa, modelo);
        this.empresaPropietaria = empresaPropietaria;
    }

    public Privado() {
    }

    public String getEmpresaPropietaria() {
        return empresaPropietaria;
    }

    public void setEmpresaPropietaria(String empresaPropietaria) {
        this.empresaPropietaria = empresaPropietaria;
    }




}

