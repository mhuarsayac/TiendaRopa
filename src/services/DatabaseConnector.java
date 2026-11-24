package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    //Al poner static ya no es necesario crear mil y un copias de database conector para cada clase, sino puededs hacerlo defrente...
    private static String url =  "jdbc:mysql://localhost:3306/non_conformist";
    private static String username = "root";
    private static String contrasena = "";
    private static Connection connection;
    //Al usar static no se cambian los datos puesto que solo pertenecen a la clase

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url,username,contrasena);
            System.out.println("La base de Datos fue conectada!");
            return connection;
        } catch (SQLException error){
            System.out.println(error.getMessage());
            return null;
        }
    }
}
