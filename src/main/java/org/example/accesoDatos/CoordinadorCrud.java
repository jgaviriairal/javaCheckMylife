package org.example.accesoDatos;

import org.example.modelo.Coordinador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoordinadorCrud {
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

        public void agregarCoordinador (Coordinador coordinadorNuevo){

            String sql = "INSERT INTO personas (nombres, apellidos, documento, celular, usuario, contrasena, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            String coordi =  "INSERT INTO coordinadores (id_coordinador, id_persona, area) VALUES (?, ?, ?)";

            try(
                    Connection conex = conectarBD();
                    PreparedStatement persona = conex.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    PreparedStatement coordinador = conex.prepareStatement(coordi);

            ){

                persona.setString(1, coordinadorNuevo.getNombres());
                persona.setString(2, coordinadorNuevo.getApellidos());
                persona.setInt(3, coordinadorNuevo.getDocumento());
                persona.setInt(4, coordinadorNuevo.getCelular());
                persona.setString(5,coordinadorNuevo.getUsuario());
                persona.setString(6,coordinadorNuevo.getContrasena());
                persona.setString(7,coordinadorNuevo.getEmail());
                persona.execute();


                ResultSet generatedKeys = persona.getGeneratedKeys();
                int idPersona = -1;
                if (generatedKeys.next()) {
                    idPersona = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID de la persona insertada.");
                }
                coordinador.setInt(1,idPersona);
                coordinador.setInt(2,idPersona);
                coordinador.setString(3, coordinadorNuevo.getArea());
                coordinador.execute();


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public List<Coordinador> buscarXDocumento(Integer documento){
            List<Coordinador>  lCoordinador =new ArrayList<>();
            String sql = "Select d.id_coordinador as id, p.documento as documento,p.nombres as nombres from coordinadores d join personas p on p.id_persona = d.id_persona where p.documento = ? ";
            try (
                    Connection cone = conectarBD();
                    PreparedStatement doc = cone.prepareStatement(sql);
            )
            {
                doc.setInt(1,documento);
                ResultSet rsDoc = doc.executeQuery();
                // Recorrer el resultset y pasar cada registro al coordinador
                while (rsDoc.next()){
                    Coordinador coordi = new Coordinador(
                            rsDoc.getString("nombres"),
                            rsDoc.getInt("documento")
                    );
                    lCoordinador.add(coordi);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return lCoordinador;
        }


        public Coordinador buscarCoordinador(String usuario, String contrasena) {

            String sql = "SELECT coordi.id_coordinador as id, perso.nombres as nombres, perso.apellidos as apellido, " +
                    " perso.usuario, perso.contrasena FROM coordinadores coordi " +
                    " JOIN personas perso ON coordi.id_persona = perso.id_persona " +
                    " WHERE perso.usuario = ? AND perso.contrasena = ?";
            try (
                    Connection conex = conectarBD();
                    PreparedStatement persona1 = conex.prepareStatement(sql);
            ) {
                persona1.setString(1, usuario);
                persona1.setString(2, contrasena);
                ResultSet result = persona1.executeQuery();

                if (result.next()){
                    return new Coordinador(
                            result.getString("nombres"),
                            result.getString("apellido")
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    public void actualizarCoordinador(Coordinador coordinadorActualizado) {
        String sqlBuscar = "SELECT p.id_persona, d.id_coordinador FROM personas p JOIN coordinadores d ON p.id_persona = d.id_persona WHERE p.documento = ?";
        String sqlactualizaPersona = "UPDATE personas SET nombres=?, apellidos=?, celular=?, usuario=?, contrasena=?, email=? WHERE id_persona=?";
        String sqlactualizaCoordinador = "UPDATE coordinadores SET aerea=? WHERE id_coordinador=?";

        try (
                Connection cnx = conectarBD();
                PreparedStatement psBuscar = cnx.prepareStatement(sqlBuscar)
        ) {
            // busca los IDs (id_persona y id_coordinador) usando el documento
            psBuscar.setInt(1, coordinadorActualizado.getDocumento());
            ResultSet rs = psBuscar.executeQuery();

            if (rs.next()) {
                int idPersona = rs.getInt("id_persona");
                int idCoordinador = rs.getInt("id_coordinador");

                //  Actualizar los datos en la tabla personas
                try (PreparedStatement actualizaPersona = cnx.prepareStatement(sqlactualizaPersona)) {
                    actualizaPersona.setString(1, coordinadorActualizado.getNombres());
                    actualizaPersona.setString(2, coordinadorActualizado.getApellidos());
                    actualizaPersona.setInt(3, coordinadorActualizado.getCelular());
                    actualizaPersona.setString(4, coordinadorActualizado.getUsuario());
                    actualizaPersona.setString(5, coordinadorActualizado.getContrasena());
                    actualizaPersona.setString(6, coordinadorActualizado.getEmail());
                    actualizaPersona.setInt(7, idPersona);

                    actualizaPersona.executeUpdate();
                }
                /*
                //  Actualizar la area en la tabla coordinadores
                try (PreparedStatement actualizaCoordinador = cnx.prepareStatement(sqlactualizaCoordinador)) {
                    actualizaCoordinador.setString(1, coordinadorActualizado.getArea());
                    actualizaCoordinador.setInt(2, idCoordinador);

                    actualizaCoordinador.executeUpdate();
                } */

                System.out.println("Datos actualizados correctamente para el documento: " + coordinadorActualizado.getDocumento());

            } else {
                System.out.println("No se encontró ningún coordinador con documento: " + coordinadorActualizado.getDocumento());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
