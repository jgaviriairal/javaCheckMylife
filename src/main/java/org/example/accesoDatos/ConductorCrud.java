package org.example.accesoDatos;

import org.example.modelo.Conductor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConductorCrud {
    /*Variables para la conexion a la bd
    se tien encuenta que la bd esta en construccion aun
    * */
    private final String url = "jdbc:mysql://localhost:3306/javacheckmylife";//queda pendiente el nombre de la BD
    private final String user ="root";
    private final String password = "";

    //Metodo para lla conexion a BD
    public Connection conectarBD ()throws SQLException{
        return DriverManager.getConnection(url,user,password);
    }

    public void agregarConductor (Conductor conductorNuevo){

        String sql = "INSERT INTO personas (nombres, apellidos, documento, celular, usuario, contrasena, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String cond =  "INSERT INTO conductores (id_conductor, id_persona, licencia) VALUES (?, ?, ?)";

        try(
                Connection conex = conectarBD();
                PreparedStatement persona = conex.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement conductor = conex.prepareStatement(cond);

                ){

            persona.setString(1,conductorNuevo.getNombres());
            persona.setString(2, conductorNuevo.getApellidos());
            persona.setInt(3,conductorNuevo.getDocumento());
            persona.setInt(4,conductorNuevo.getCelular());
            persona.setString(5,conductorNuevo.getUsuario());
            persona.setString(6,conductorNuevo.getContrasena());
            persona.setString(7,conductorNuevo.getEmail());
            persona.execute();


            ResultSet generatedKeys = persona.getGeneratedKeys();
            int idPersona = -1;
            if (generatedKeys.next()) {
                idPersona = generatedKeys.getInt(1);
            } else {
                throw new SQLException("No se pudo obtener el ID de la persona insertada.");
            }
            conductor.setInt(1,idPersona);
            conductor.setInt(2,idPersona);
            conductor.setString(3, conductorNuevo.getLicencia());
            conductor.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Conductor> buscarXDocumento(Integer documento){
        List<Conductor>  lConductor =new ArrayList<>();
        String sql = "Select d.id_conductor as id, p.documento as documento,p.nombres as nombres from conductores d join personas p on p.id_persona = d.id_persona where p.documento = ? ";
        try (
                Connection con = conectarBD();
                PreparedStatement doc = con.prepareStatement(sql);
                )
        {
           doc.setInt(1,documento);
           ResultSet rsDoc = doc.executeQuery();
            // Recorrer el resultset y pasar cada registro al conductor
            while (rsDoc.next()){
                Conductor cond = new Conductor(
                        rsDoc.getString("nombres"),
                        rsDoc.getInt("documento")
                );
                lConductor.add(cond);
            }

        } catch (SQLException e) {
           e.printStackTrace();
        }
        return lConductor;
    }


    public Conductor buscarConductor(String usuario, String contrasena) {

        String sql = "SELECT condu.id_conductor as id, perso.nombres as nombres, perso.apellidos as apellido, " +
                " perso.usuario, perso.contrasena FROM conductores condu " +
                " JOIN personas perso ON condu.id_persona = perso.id_persona " +
                " WHERE perso.usuario = ? AND perso.contrasena = ?";
        try (
                Connection conex = conectarBD();
                PreparedStatement persona1 = conex.prepareStatement(sql);
        ) {
            persona1.setString(1, usuario);
            persona1.setString(2, contrasena);
            ResultSet result = persona1.executeQuery();

            if (result.next()){
                return new Conductor(
                        result.getString("nombres"),
                        result.getString("apellido")

                        );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void actualizarConductor(Conductor conductorActualizado) {
        String sqlBuscar = "SELECT p.id_persona, d.id_conductor FROM personas p JOIN conductores d ON p.id_persona = d.id_persona WHERE p.documento = ?";
        String sqlactualizaPersona = "UPDATE personas SET nombres=?, apellidos=?, celular=?, usuario=?, contrasena=?, email=? WHERE id_persona=?";
        String sqlactualizaConductor = "UPDATE conductores SET licencia=? WHERE id_conductor=?";

        try (
                Connection cnx = conectarBD();
                PreparedStatement psBuscar = cnx.prepareStatement(sqlBuscar)
        ) {
            // busca los IDs (id_persona y id_conductor) usando el documento
            psBuscar.setInt(1, conductorActualizado.getDocumento());
            ResultSet rs = psBuscar.executeQuery();

            if (rs.next()) {
                int idPersona = rs.getInt("id_persona");
                int idConductor = rs.getInt("id_conductor");

                //  Actualizar los datos en la tabla personas
                try (PreparedStatement actualizaPersona = cnx.prepareStatement(sqlactualizaPersona)) {
                    actualizaPersona.setString(1, conductorActualizado.getNombres());
                    actualizaPersona.setString(2, conductorActualizado.getApellidos());
                    actualizaPersona.setInt(3, conductorActualizado.getCelular());
                    actualizaPersona.setString(4, conductorActualizado.getUsuario());
                    actualizaPersona.setString(5, conductorActualizado.getContrasena());
                    actualizaPersona.setString(6, conductorActualizado.getEmail());
                    actualizaPersona.setInt(7, idPersona);

                    actualizaPersona.executeUpdate();
                }

                //  Actualizar la licencia en la tabla conductores
                try (PreparedStatement actualizaConductor = cnx.prepareStatement(sqlactualizaConductor)) {
                    actualizaConductor.setString(1, conductorActualizado.getLicencia());
                    actualizaConductor.setInt(2, idConductor);

                    actualizaConductor.executeUpdate();
                }

                System.out.println("Datos actualizados correctamente para el documento: " + conductorActualizado.getDocumento());

            } else {
                System.out.println("No se encontró ningún conductor con documento: " + conductorActualizado.getDocumento());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
