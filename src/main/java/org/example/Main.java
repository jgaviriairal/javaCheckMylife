package org.example;

import org.example.accesoDatos.ConductorCrud;
import org.example.accesoDatos.VehiculoCrud;
import org.example.modelo.Conductor;
import org.example.modelo.Privado;
import org.example.modelo.Coordinador;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("holaaaa");
        Scanner sc =new Scanner(System.in);
        Conductor con=new Conductor();
        Privado veh = new Privado();
        ConductorCrud nuevoCon = new ConductorCrud();
        Coordinador coordi = new Coordinador();
        //instancia de conexion
        Connection cnx = null;
        try{
            cnx =nuevoCon.conectarBD();
            if (cnx != null && !cnx.isClosed()) {
                //menu para el CRUD para la tabla de vehiculo
               boolean menu = false;

                do{
                    System.out.println("---Bienvenido elija una opción---");
                    System.out.println("---1. Conductor-------------------");
                    System.out.println("---2. Coordinador-----------------");
                    System.out.println("---3. Salir-----------------------");
                    System.out.print("Opción : ");
                     int opcion = sc.nextInt();
                    sc.nextLine();
                    switch (opcion){
                        case 1:
                               int opcionC;
                               boolean volverInicioC = false;
                            do {
                                System.out.println("---------CONDUCTOR-------------");
                                System.out.println("---1. INICIA SESIÓN------------");
                                System.out.println("---2. REGISTRATE---------------");
                                System.out.println("---3. ACTUALIZAR---------------");
                                System.out.println("---4. VOLVER AL MENU ANTERIOR--");
                                System.out.print("Opción : ");
                                opcionC = sc.nextInt();
                                sc.nextLine();
                                switch (opcionC){
                                    case 1:
                                        // LLama el metodo iniciar sesion conductor
                                        con.IniciarSesion();
                                        volverInicioC=true;
                                        break;
                                    case 2:
                                        // se llama la funcion Registro de la clase conductor
                                        con.Registro();
                                        volverInicioC = true;
                                        break;
                                    case 3:
                                        //se llama la funcion actualizar Datos de la clase conductor
                                        con.ActualizarDatos();
                                        volverInicioC = true;
                                        break;
                                    case 4:
                                        System.out.println("te devolviste");
                                        volverInicioC= true;
                                        break;
                                    default:
                                        System.out.println("Opción incorrecta....");
                                }
                            }
                            while (!volverInicioC);
                            break;
                        case 2:
                                int opcionD;
                                boolean volverInicioD = false;
                            do {
                                System.out.println("---------COORDINADOR-----------");
                                System.out.println("---1. INICIA SESIÓN------------");
                                System.out.println("---2. REGISTRATE---------------");
                                System.out.println("---3. ACTUALIZAR---------------");
                                System.out.println("---3. VOLVER AL MENU ANTERIOR--");
                                System.out.print("Opción : ");
                                opcionD = sc.nextInt();
                                sc.nextLine();
                                switch (opcionD){
                                    case 1:
                                        coordi.IniciarSesion();
                                        int opcionCoo;
                                        boolean  volverInicioCoor = false;
                                        do {
                                            System.out.println("--------- MENU COORDINADOR-----------");
                                            System.out.println("---1. Ingresa Vehiculo------------");
                                            System.out.println("---2. Buscar vehiculos---------------");
                                            System.out.println("---3. VOLVER AL MENU ANTERIOR--");
                                            System.out.print("Opción : ");
                                            opcionCoo = sc.nextInt();
                                            sc.nextLine();
                                            switch (opcionCoo){
                                            case 1:
                                                veh.ingresarVehiculo();
                                                break;
                                            case 2:
                                                veh.buscarVehiculo();
                                                break;
                                            case 3 :
                                                System.out.println("te devolviste");
                                                volverInicioCoor= true;
                                                break;
                                            default:{
                                                   System.out.println("Opción incorrecta....");
                                                 }
                                            }
                                        }while (!volverInicioCoor);
                                        break;
                                        case 2:
                                            // se llama la funcion Reggistro de la clase coordinador
                                            coordi.Registro();
                                            volverInicioD = true;
                                        break;
                                    case 3:
                                        //se llama la funcion actualizar Datos de la clase coordinador
                                        coordi.ActualizarDatos();
                                        volverInicioD = true;
                                        break;
                                        case 4:
                                            System.out.println("te devolviste");
                                            volverInicioD= true;
                                        break;
                                    default:
                                        System.out.println("Opción incorrecta....");
                                }
                            }while (!volverInicioD);
                        break;
                        case 3:
                            System.out.println("Hasta luego....");
                            menu =true;
                            break;
                        default:
                            System.out.println("Opción incorrecta....");
                    }
                }
                while (!menu);
            }
        }
        catch (SQLException e){
            System.out.println("no se puede conectar a la base de datos");
        }
    }
}