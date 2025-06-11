package org.example.modelo;
public abstract class  Vehiculo implements IAsignacionRuta,IMonitoreo {
    private String placa;
    private String marca;
    private String modelo;

    public Vehiculo() {
    }

    public Vehiculo(String placa,String marca, String modelo) {
        this.marca = marca;
        this.placa = placa;
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void encender() {
        System.out.println("Vehículo encendido");
    }

    public void apagar() {
        System.out.println("Vehículo apagado");
    }

    @Override
    public void AsignacionRuta(Ruta ruta) {
        System.out.println("Ruta asignada al vehículo: " + ruta.obtenerInfo());
    }

    @Override
    public void Monitoreo() {

    }
    public abstract void ingresarVehiculo();


}
