package org.example.modelo;

import org.example.accesoDatos.ConductorCrud;
import org.example.accesoDatos.CoordinadorCrud;

import java.util.Scanner;

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
    public void IniciarSesion() {

        CoordinadorCrud iniCoordi= new CoordinadorCrud();
        Scanner sc =new Scanner(System.in);

        //Pide los datos del usuario o condctor
        System.out.println("-----INICIO SESION COORDINADOR---");
        System.out.print("Usuario :  ");
        String  usuarioIn =sc.nextLine();
        System.out.print("Contraseña :  ");
        String contrasenaIn =sc.nextLine();

        Coordinador coordinador= iniCoordi.buscarCoordinador(usuarioIn, contrasenaIn);

        if (coordinador != null){
            System.out.println("Iniciaste Correctamente");
            System.out.println("Bienvenido, " +coordinador.getNombres() + " " + coordinador.getApellidos() + ".");

        }else {
            System.out.println("Usuario o contraseña incorrectos. Inténtalo de nuevo.");
        }

    }


    @Override
    public void Monitoreo() {
        System.out.println("el vehiculo con el conductor :"+getNombres()+ "se encuentra en ...");
    }
    public Coordinador(String nombres, Integer documento) {
        super(nombres, documento);

    }
    public Coordinador(String nombres, String apellido){
        super(nombres, apellido);

    }

    public void Registro(){
        CoordinadorCrud coordi=new CoordinadorCrud();
        Scanner sc =new Scanner(System.in);
        System.out.println("---REGISTRO------");
        System.out.print("Nombres :  ");
        String nombres =sc.nextLine();
        System.out.print("Apellidos :  ");
        String apellidos =sc.nextLine();
        System.out.print("Documento :  ");
        int documento =sc.nextInt();
        sc.nextLine();
        System.out.print("celular :  ");
        int celular =sc.nextInt();
        sc.nextLine();
        System.out.print("Licencia :  ");
        String  licencia =sc.nextLine();
        System.out.print("Usuario :  ");
        String  usuario =sc.nextLine();
        System.out.print("Contraseña :  ");
        String contrasena =sc.nextLine();
        System.out.print("Repita Contraseña :  ");
        String repitaContrasena =sc.nextLine();
        System.out.print("Email :  ");
        String email =sc.nextLine();

        if (contrasena.equals(repitaContrasena ) && coordi.buscarXDocumento(documento).isEmpty()  ) {
            coordi.agregarCoordinador(new Coordinador(nombres, apellidos, documento, celular, usuario, contrasena, email));
            System.out.println("Coordinador agregado...");

        }
        else {
            System.out.println("las constraseñas deben ser iguales o el usuario ya exite... intentelo nuevamente");
        }
    }
}
