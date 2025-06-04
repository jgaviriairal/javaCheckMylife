package org.example.modelo;

public class Particular extends Vehiculo {

    private boolean usoPersonal;

    public Particular() {
    }

    public Particular(String marca, String placa, String modelo, boolean usoPersonal) {
        super(marca, placa, modelo);
        this.usoPersonal = usoPersonal;
    }

    public boolean isUsoPersonal() {
        return usoPersonal;
    }

    public void setUsoPersonal(boolean usoPersonal) {
        this.usoPersonal = usoPersonal;
    }
}

