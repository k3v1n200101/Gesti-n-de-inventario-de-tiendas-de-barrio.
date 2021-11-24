package com.ucentral.Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {


    public static Connection getConexion() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/Inventario";
        String user = "root";
        String password = "1234";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("conexion exitosa");
            return connection;
        } catch (SQLException sqlException) {
            sqlException.getMessage();
            sqlException.printStackTrace();
        }
        return null;
    }

    public static Connection conectar() {
        Connection con = null;

        String password = "1234";
        String usuario = "root";
        String url = "jdbc:mysql://localhost:3306/Inventario?user=" + usuario
                + "&password=" + password;
        try {
            con = DriverManager.getConnection(url);
            if (con != null) {
                System.out.println("Conectado");
            }
        } catch (SQLException e) {
            System.out.println("No se pudo conectar a la base de datos");
            e.printStackTrace();
        }
        return con;
    }

}
