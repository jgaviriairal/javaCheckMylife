package org.example.modelo;

import java.time.LocalDate;

public class Ruta {
    private String idRuta;
    private String origen;
    private String destino;
    private Double distancia;
    private LocalDate fechaInicio;
    private LocalDate fechaTerminacion;

    public Ruta() {
    }

    public Ruta(String origen, String idRuta, String destino, Double distancia, LocalDate fechaInicio, LocalDate fechaTerminacion) {
        this.origen = origen;
        this.idRuta = idRuta;
        this.destino = destino;
        this.distancia = distancia;
        this.fechaInicio = fechaInicio;
        this.fechaTerminacion = fechaTerminacion;
    }

    public String getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(String idRuta) {
        this.idRuta = idRuta;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaTerminacion() {
        return fechaTerminacion;
    }

    public void setFechaTerminacion(LocalDate fechaTerminacion) {
        this.fechaTerminacion = fechaTerminacion;
    }

    public String obtenerInfo (){ return origen + " -> " + destino + " (" + distancia + " km)"+ "fecha de inicio : "+fechaInicio;

    }
}
