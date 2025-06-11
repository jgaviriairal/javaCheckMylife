package org.example.accesoDatos;

import org.example.modelo.Particular;
import org.example.modelo.Privado;
import org.example.modelo.Vehiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculoCrud {
    /*Variables para la conexion a la bd
  se tien encuenta que la bd esta en construccion aun
  * */
    private final String url = "jdbc:mysql://localhost:3306/javacheckmylife";//queda pendiente el nombre de la BD
    private final String user ="root";
    private final String password = "";

    //Metodo para lla conexion a BD
    public Connection conectarBD ()throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

    public  void agregarVehiculo (Privado vehiculo){
        String sql = "insert into vehiculos (placa, marca, modelo) values (?,?,?)";
        String cond =  "INSERT INTO vehiculos_privados (id_vehiculo_privado, id_vehiculo, empresa_propietario) VALUES (?, ?, ?)";


        try ( Connection con =conectarBD();
              PreparedStatement consulta = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
              PreparedStatement privado = con.prepareStatement(cond);

              )
        {
            consulta.setString(1, vehiculo.getPlaca());
            consulta.setString(2,vehiculo.getMarca());
            consulta.setString(3,vehiculo.getModelo());
            consulta.execute();

            ResultSet generatedKeys = consulta.getGeneratedKeys();
            int idvehiculo = -1;
            if (generatedKeys.next()) {
                idvehiculo = generatedKeys.getInt(1);
            } else {
                throw new SQLException("No se pudo obtener el ID de la persona insertada.");
            }
            privado.setInt(1,idvehiculo);
            privado.setInt(2,idvehiculo);
            privado.setString(3, vehiculo.getEmpresaPropietaria());
            privado.execute();

            System.out.println("vehiculo  con placa : "+vehiculo.getPlaca()+ " agregado correctamente" );
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean BuscarXPlaca (String placa){

        String sql = "SELECT v.placa ,v.marca ,v.modelo FROM vehiculos v WHERE v.placa = ?";

        try (
                Connection con =conectarBD();
                PreparedStatement consulta = con.prepareStatement(sql);
                ){
                consulta.setString(1, placa);
                 ResultSet rs = consulta.executeQuery();

            if (rs.next()) {
                System.out.println("Vehículo con placa: " + placa + " ya se encuentra en la base de datos.");
                return true; // Sí encontró el vehículo
            } else {
                System.out.println("Vehículo con placa: " + placa + " no se encuentra en la base de datos.");
                return false; // No encontró el vehículo
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Vehiculo> buscarVehiculo(){
        List<Vehiculo> lvehiculo = new ArrayList<>();
        String query = "Select placa, marca, modelo From Vehiculos";
        try(
                Connection myconn = conectarBD();
                PreparedStatement stm = myconn.prepareStatement(query);
        )
        {

            ResultSet rsveh = stm.executeQuery();
            // Recorrer el resultset y pasar cada registro al arreglo lvehiculo
            while(rsveh.next()){
                Privado ovh = new Privado (
                        rsveh.getString("placa"),
                        rsveh.getString("marca"),
                        rsveh.getString("modelo")
                );
                lvehiculo.add(ovh);
            }
        }
        catch (SQLException err){
            err.printStackTrace();
        }
        return lvehiculo;
    }
}
