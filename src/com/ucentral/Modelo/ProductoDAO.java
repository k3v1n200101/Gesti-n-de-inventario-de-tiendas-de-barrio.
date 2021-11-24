package com.ucentral.Modelo;

import javax.naming.ldap.Control;
import java.sql.*;
import java.util.ArrayList;

import com.ucentral.controlador.*;

public class ProductoDAO implements Producto {
    PreparedStatement preparedStatement;

    @Override

    public boolean ingresar_mercancia(String nombre, int cantidad) {
        boolean paso = false;
        Connection co;
        Statement stm;
        ResultSet rs;
        String sql = "INSERT INTO inv002_producto (PRODUC_NOMBRE,PRODUC_CANTIDAD) VALUES(?,?)";

        try {
            co = Conexion.conectar();

            preparedStatement = co.prepareStatement(sql);
            preparedStatement.setString(1, nombre);
            preparedStatement.setInt(2, cantidad);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                System.out.println("vsvuisdzbuisduieshjisdh");
                paso = true;
            }
            preparedStatement.close();

            co.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase ClienteDaoImple, método obtener");
            e.printStackTrace();
            paso = false;
        }
        return paso;
    }

    @Override
    public boolean acabar_existencias(int Identificacion) {
        boolean paso = false;
        Connection co;
        Statement stm;
        ResultSet rs;

        String sql = "DELETE FROM inv002_producto WHERE PRODUC_ID =?";

        try {
            co = Conexion.conectar();
            preparedStatement = co.prepareStatement(sql);
            preparedStatement.setInt(1, Identificacion);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                System.out.println("vsvuisdzbuisduieshjisdh");
                paso = true;

            }
            preparedStatement.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase ClienteDaoImple, método obtener");
            e.printStackTrace();
            paso = false;
        }
        return paso;
    }

    @Override
    public boolean cambiarDatos_producto(int Identificador, String nombre, int cantidad) {
        Connection co;
        boolean paso = false;
        String sql = "UPDATE inv002_producto SET PRODUC_NOMBRE=?, PRODUC_CANTIDAD= ? WHERE PRODUC_ID=?";

        try {
            co = Conexion.conectar();
            preparedStatement = co.prepareStatement(sql);
            preparedStatement.setString(1, nombre);
            preparedStatement.setInt(2, cantidad);
            preparedStatement.setInt(3, Identificador);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                System.out.println("vsvuisdzbuisduieshjisdh");
                paso = true;
            }
            co.close();
        } catch (SQLException e) {
            System.out.println("ERROR UPDATE PRODUCTO " + e);
            e.printStackTrace();
            paso = false;
        }
        return paso;
    }

    @Override
    public ArrayList<modeloProducto> enlistar_Productos() {
        Connection co;
        Statement stm;
        ResultSet rs;

        String sql = "SELECT * FROM inv002_producto";

        ArrayList<modeloProducto> listaProducto = new ArrayList<>();

        try {
            co = Conexion.conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                modeloProducto Producto = new modeloProducto();
                Producto.setIdentificador(rs.getInt(1));
                Producto.setNombre(rs.getString(2));
                Producto.setCantidad(rs.getInt(3));
                listaProducto.add(Producto);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase ClienteDaoImple, método obtener");
            e.printStackTrace();
        }

        return listaProducto;
    }
}


