package com.ucentral.Modelo;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO implements Usuario {

    ArrayList<modeloUsuario> usuarios = new ArrayList<>();
    PreparedStatement preparedStatement;

    @Override
    public boolean validar_usuario(String nombre, String contraseña) {
        ArrayList<modeloUsuario> Comprobar = new ArrayList<modeloUsuario>();
        boolean estado = false;
        try {
            Comprobar = enlistar_usuarios();
            int i = 0;
            while (i < Comprobar.size()) {
                if (Comprobar.get(i).getNombre().equals(nombre) && Comprobar.get(i).getContraseña().equals(contraseña)) {
                    estado = true;
                    break;
                } else {
                    i++;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estado;
    }

    //
    @Override
    public int validar_usuarionvl(String NUsuario) {
        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;

        String sql = "SELECT USUARI_NVL " +
                "FROM cat001_usuario " +
                "WHERE USUARI_NOMBRE= '" + NUsuario + "'";

        try {
            co = Conexion.conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            int nvl = 0;
            while (rs.next()) {
                nvl = rs.getInt(1);
            }
            stm.close();
            rs.close();
            co.close();
            return nvl;
        } catch (SQLException e) {
            System.out.println("Error: no funcion" + e);
            e.printStackTrace();
        }
        return 0;
    }

    ///
    @Override
    public boolean registrar_usuario(String nombre, String Nidentifiacion, int TpIdentificacion, String contraseña) {
        Connection co;
        boolean paso = false;
        String sql = "INSERT INTO cat001_usuario" +
                "(USUARI_NOMBRE,USUARI_NIDENTIFIACION,USUARI_TPIDENTIFICACION,USUARI_CONTRASEÑA)" +
                "values (?,?,?,?)";

        try {
            co = Conexion.conectar();
            preparedStatement = co.prepareStatement(sql);
            preparedStatement.setString(1,nombre);
            preparedStatement.setString(2,Nidentifiacion);
            preparedStatement.setInt(3,TpIdentificacion);
            preparedStatement.setString(4,contraseña);
            int i = preparedStatement.executeUpdate();
            if(i>0){
                System.out.println("funciona Registrar");
                paso = true;
            }
            co.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase ClienteDaoImple, método obtener");
            e.printStackTrace();
            paso = false;
        }
        return paso;
    }

    @Override
    public boolean finalizar_contrato(String Nidentifiacion) {
        Connection co ;
        boolean paso = false;
        String sql = "DELETE FROM cat001_usuario WHERE USUARI_NIDENTIFIACION = ?";

        try {
            co = Conexion.conectar();
            preparedStatement = co.prepareStatement(sql);
            preparedStatement.setString(1,Nidentifiacion);
            int i = preparedStatement.executeUpdate();
            if(i>0){
                System.out.println("funciona Registrar");
                paso = true;
            }
            co.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase ClienteDaoImple, método obtener");
            e.printStackTrace();
            paso = false;
        }
        return paso;
    }

    @Override
    public boolean cambiarDatos_usuario(String nombre, String Nidentifiacion, int TpIdentificacion, String contraseña) {
        Connection co;
        boolean paso = false;

        String sql = "UPDATE cat001_usuario SET USUARI_NOMBRE=?,USUARI_NIDENTIFIACION=?,USUARI_TPIDENTIFICACION=?,USUARI_CONTRASEÑA=? WHERE ID= ?";

        try {
            co = Conexion.conectar();
            preparedStatement = co.prepareStatement(sql);
            preparedStatement.setString(1,nombre);
            preparedStatement.setString(2,Nidentifiacion);
            preparedStatement.setInt(3,TpIdentificacion);
            preparedStatement.setString(4,contraseña);
            int i = preparedStatement.executeUpdate();
            if(i>0){
                System.out.println("funciona Registrar");
                paso = true;
            }
            co.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase ClienteDaoImple, método obtener");
            e.printStackTrace();
            paso = false;
        }
        return paso;
    }

    @Override
    public ArrayList<modeloUsuario> enlistar_usuarios() throws ClassNotFoundException, SQLException {
        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM cat001_usuario";

        ArrayList<modeloUsuario> listausuario = new ArrayList<modeloUsuario>();

        try {
            co = Conexion.conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                modeloUsuario user = new modeloUsuario();
                user.setNombre(rs.getString(2));
                user.setContraseña(rs.getString(6));
                user.setCedula(rs.getString(3));
                user.setNvl(rs.getInt(5));
                listausuario.add(user);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase ClienteDaoImple, método obtener");
            e.printStackTrace();
        }

        return listausuario;
    }
}
