package org.example.modelo;
public class Coordinador extends Persona implements IAsignacionRuta {
    private String area;

    public Coordinador() {
    }

    public Coordinador(String nombres, String apellidos, Integer documento, Integer celular, String usuario, String contrasena, String email) {
        super(nombres, apellidos, documento, celular, usuario, contrasena, email);
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }


    @Override
    public void AsignacionRuta(Ruta fruta) {
        System.out.println("Ruta ingresada por el coordinador: " + fruta.obtenerInfo());
    }
    @Override
    public void IniciarSesion(String usuario, String contrasena) {
        System.out.println("hola iniciaste sesión"+ this.getNombres());
    }

    @Override
    public void Monitoreo() {
        System.out.println("el vehiculo con el conductor :"+getNombres()+ "se encuentra en ...");
    }
}
