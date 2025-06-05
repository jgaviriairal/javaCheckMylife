package org.example.accesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConductorCrud {
    /*Variables para la conexion a la bd
    se tien encuenta que la bd esta en construccion aun
    * */
    private final String url = "jdbc:mysql://localhost:3306/";//queda pendiente el nombre de la BD
    private final String user ="root";
    private final String password = "";

    //Metodo para lla conexion a BD
    public Connection conectarBD ()throws SQLException{
        return DriverManager.getConnection(url,user,password);
    }

}
