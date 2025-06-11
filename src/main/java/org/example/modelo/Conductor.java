package org.example.modelo;

import org.example.accesoDatos.ConductorCrud;

import java.util.Scanner;

public class Conductor extends Persona implements IAsignacionRuta {
    private String licencia;
    private String vehiculoAsignado;
    private String huella;
    private Vehiculo asignarVehiculo;

    public Conductor() {
    }

    public Conductor(String nombres, String apellidos, Integer documento, Integer celular, String usuario, String contrasena, String email, String licencia, String vehiculoAsignado, String huella) {
        super(nombres, apellidos, documento, celular, usuario, contrasena, email);
        this.licencia = licencia;
        this.vehiculoAsignado = vehiculoAsignado;
        this.huella = huella;
    }

    public Conductor(String nombres, String apellidos, Integer documento, Integer celular, String usuario, String contrasena, String email, String licencia) {
        super(nombres, apellidos, documento, celular, usuario, contrasena, email);
        this.licencia = licencia;

    }

    public Conductor(String nombres, Integer documento) {
        super(nombres, documento);

    }
    public Conductor(String nombres, String apellido){
        super(nombres, apellido);
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


    public void IniciarSesion(){

        ConductorCrud iniCond= new ConductorCrud();
        Scanner sc =new Scanner(System.in);

        //Pide los datos del usuario o condctor
        System.out.println("-----INICIO SESION CONDUCTOR---");
        System.out.print("Usuario :  ");
        String  usuarioIn =sc.nextLine();
        System.out.print("Contraseña :  ");
        String contrasenaIn =sc.nextLine();

        Conductor conductor= iniCond.buscarConductor(usuarioIn, contrasenaIn);

        if (conductor != null){
            System.out.println("Iniciaste Correctamente");
            System.out.println("Bienvenido, " +conductor.getNombres() + " " + conductor.getApellidos() + ".");

        }else {
            System.out.println("Usuario o contraseña incorrectos. Inténtalo de nuevo.");
        }

        }

        public void Registro(){
            ConductorCrud cond=new ConductorCrud();
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

            if (contrasena.equals(repitaContrasena ) && cond.buscarXDocumento(documento).isEmpty()  ) {
                cond.agregarConductor(new Conductor(nombres, apellidos, documento, celular, usuario, contrasena, email, licencia));
                System.out.println("conducto agregado...");

            }
            else {
                System.out.println("las constraseñas deben ser iguales o el usuario ya exite... intentelo nuevamente");
            }
        }
    public void ActualizarDatos(){

        Conductor conductorActualizado = new Conductor();
        ConductorCrud cond=new ConductorCrud();
        Scanner sc = new Scanner(System.in);

        System.out.println("---Actualizacion de datos del conductor---");
        System.out.print("documento del conductor a actualizar: ");
        Integer documento = sc.nextInt();
        sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Celular: ");
        Integer celular = sc.nextInt();
        sc.nextLine();
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();
        System.out.print("E-mail: ");
        String mail =sc.nextLine();
        System.out.print("Licencia: ");
        String licencia = sc.nextLine();

        conductorActualizado.setDocumento(documento);
        conductorActualizado.setNombres(nombre);
        conductorActualizado.setApellidos(apellido);
        conductorActualizado.setCelular(celular);
        conductorActualizado.setUsuario(usuario);
        conductorActualizado.setContrasena(contrasena);
        conductorActualizado.setEmail(mail);
        conductorActualizado.setLicencia(licencia);

        cond.actualizarConductor(conductorActualizado);

        System.out.println("Actualizacion de los datos del conductor realizada");

    }
}
