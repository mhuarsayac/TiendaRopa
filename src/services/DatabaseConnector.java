package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String url = "jdbc:mysql://localhost:3306/non_conformist";
    private static final String usuario = "root";
    private static final String contrasena = "";
    private static Connection connection;

    public static Connection conectarBaseDeDatos() {
        try {
            connection = DriverManager.getConnection(url, usuario, contrasena);
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
