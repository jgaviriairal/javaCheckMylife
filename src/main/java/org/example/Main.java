package org.example;

import org.example.accesoDatos.ConductorCrud;
import org.example.accesoDatos.VehiculoCrud;
import org.example.modelo.Conductor;
import org.example.modelo.Particular;
import org.example.modelo.Privado;
import org.example.modelo.Vehiculo;

import org.example.accesoDatos.CoordinadorCrud;
import org.example.modelo.Coordinador;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("holaaaa");
        Scanner sc =new Scanner(System.in);
        Conductor con=new Conductor();
        Privado veh = new Privado();
        ConductorCrud nuevoCon = new ConductorCrud();
        VehiculoCrud vehCrud =new VehiculoCrud();
        Coordinador coordi = new Coordinador();
        

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
                                System.out.println("---3. VOLVER AL MENU ANTERIOR--");
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
                                        // se lllama la funcion Reggistro de la clase conductor
                                        con.Registro();
                                        volverInicioC = true;
                                        break;
                                    case 3:
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

                                            }
                                        while (!volverInicioCoor);
                                        break;

                                      
                                       
      
                                    case 2:
                                        // se lllama la funcion Reggistro de la clase conductor
                                        coordi.Registro();
                                        volverInicioD = true;
                                        break;
                                    case 3:
                                        System.out.println("te devolviste");
                                        volverInicioD= true;
                                        break;
                                    default:
                                        System.out.println("Opción incorrecta....");
                                }
                            }
                            while (!volverInicioD);

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