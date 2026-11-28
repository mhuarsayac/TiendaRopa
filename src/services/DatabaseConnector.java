package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    //Al poner static ya no es necesario crear mil y un copias de database conector para cada clase, sino puededs hacerlo defrente...
    private static String url = "jdbc:mysql://localhost:3306/non_conformist";
    private static String username = "root";
    private static String contrasena = "";
    //Al usar static no se cambian los datos puesto que solo pertenecen a la clase

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, contrasena);
        } catch (SQLException error) {
            System.out.println("No se pudo conectar a la base de datos: " + error.getMessage());
            return null;
        }
    }

    //probamos la conexion antes de iniciar el programa
    public static boolean probarConexion() {
        //creamos una conexion temporal y la cerramos automaticamente al finalizar el try
        try (Connection cn = DriverManager.getConnection(url, username, contrasena)) {
            return cn.isValid(3);
        } catch (SQLException error) {
            System.out.println("Error de conexion: " + error.getMessage());
            if (error.getMessage() != null && error.getMessage().contains("No suitable driver")) {
                System.out.println("Falta el driver: agrega mysql-connector-j a las librerias del proyecto");
            }
            return false;
        }
    }
}
