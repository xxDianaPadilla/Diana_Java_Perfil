package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClaseConexion {
        private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USUARIO = "DIANA_DEVELOPER";
    private static final String CONTRASENA = "Diana#2006";
    
    public static Connection getConexion() {
        try {
            // Cargar el driver JDBC
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Obtener la conexión en una variable
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            
            // Retornamos la variable que tiene la conexión
            return conexion;
        } catch (SQLException e) {
            System.out.println("Este es el error" + e);
              return null;
        } catch (ClassNotFoundException ex) {
            System.out.println("Este es el error de la clase" + ex);
              return null;
        }
      
    }
}
