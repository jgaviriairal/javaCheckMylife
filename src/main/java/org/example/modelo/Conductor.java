package org.example.modelo;

public class Conductor extends Persona implements IAsignacionRuta {
    private String licencia;
    private String vehiculoAsignado;
    private String huella;
    private Vehiculo asignarVehiculo;

    public Conductor() {
    }

    public Conductor(String nombres, String apellidos, Integer documento, Integer celular, String usuario, String contrasena, String repitaContrasena, String email, String licencia, String vehiculoAsignado, String huella) {
        super(nombres, apellidos, documento, celular, usuario, contrasena, email);
        this.licencia = licencia;
        this.vehiculoAsignado = vehiculoAsignado;
        this.huella = huella;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getVehiculoAsignado() {
        return vehiculoAsignado;
    }

    public void setVehiculoAsignado(String vehiculoAsignado) {
        this.vehiculoAsignado = vehiculoAsignado;
    }

    public String getHuella() {
        return huella;
    }

    public void setHuella(String huella) {
        this.huella = huella;
    }



    public Vehiculo getAsignarVehiculo() {
        return asignarVehiculo;
    }

    public void setAsignarVehiculo(Vehiculo asignarVehiculo) {
        this.asignarVehiculo = asignarVehiculo;
    }

    public  void asignarVehiculo(Vehiculo vehiculo){
        this.asignarVehiculo = vehiculo;
        System.out.println("hola conductor : " +getNombres() + "se asigno el vehiculo"+ vehiculo.getPlaca());

    }

    public  void AsignacionRuta(Ruta ruta){

        System.out.println("La ruta: "+ruta.getOrigen()+" ha sido asignado al conductor: "+getNombres());

    }
    public void Monitoreo(){
        System.out.println("el conductor: "+getNombres()+" del vehiculo: "+asignarVehiculo.getPlaca()+" esta conduciendo bien");
    }

    public void IniciarSesion(String usuario, String contrasena){
        System.out.println("el usuario: "+getUsuario()+" ha ingresado con exito");
    }
}
