package org.example.modelo;

import org.example.accesoDatos.VehiculoCrud;

import java.util.Scanner;

public class Privado extends Vehiculo implements IMonitoreo{

    private String empresaPropietaria;

    public Privado (String marca, String placa, String modelo, String empresaPropietaria) {
        super(marca, placa, modelo);
        this.empresaPropietaria = empresaPropietaria;
    }

    public Privado() {
    }

    public Privado(String placa, String marca, String modelo) {
        super(placa, marca, modelo);
    }

    public String getEmpresaPropietaria() {
        return empresaPropietaria;
    }

    public void setEmpresaPropietaria(String empresaPropietaria) {
        this.empresaPropietaria = empresaPropietaria;
    }

    @Override
    public void ingresarVehiculo() {
        Scanner sc =new Scanner(System.in);
        System.out.print("Ingrese la placa :" );
        String placa = sc.next();
        System.out.print("Ingrese la marca :" );
        String marca = sc.next();
        System.out.print("Ingrese la modelo :" );
        String modelo = sc.next();
        System.out.print("Ingrese la empresa :" );
        String empresa = sc.next();
        VehiculoCrud veh = new VehiculoCrud();
        if (!veh.BuscarXPlaca(placa) ){
            veh.agregarVehiculo(new Privado(placa,marca,modelo,empresa));
        }
        else {
            System.out.println("el vehiculo ya existe");
        }
    }
    public void buscarVehiculo(){
        VehiculoCrud vehCrud =new VehiculoCrud();
        System.out.println("lista de vehiculo");
        vehCrud.buscarVehiculo().forEach(x -> System.out.println(x.getPlaca()+" - "+ x.getMarca()+" - "+ x.getModelo()));

    }
}

