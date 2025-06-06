package org.example;

import org.example.accesoDatos.ConductorCrud;
import org.example.modelo.Conductor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("holaaaa");
        Scanner sc =new Scanner(System.in);
        ConductorCrud nuevoCon = new ConductorCrud();
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
                                        break;
                                    case 2:
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

                                        if (contrasena.equals(repitaContrasena ) && nuevoCon.buscarXDocumento(documento).isEmpty()  ) {
                                            nuevoCon.agregarConductor(new Conductor(nombres, apellidos, documento, celular, usuario, contrasena, email, licencia));
                                            System.out.println("conducto agregado...");
                                            volverInicioC = true;
                                        }
                                        else {
                                            System.out.println("las constraseñas deben ser iguales o el usuario ya exite... intentelo nuevamente");
                                            volverInicioC = true;
                                        }
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
                                 opcionD = sc.nextInt();
                                sc.nextLine();
                                switch (opcionD){
                                    case 1:
                                        break;
                                    case 2:
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