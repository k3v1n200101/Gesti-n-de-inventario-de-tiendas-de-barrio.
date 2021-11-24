package com.ucentral.Modelo;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Usuario {

    boolean validar_usuario(String nombre, String contraseña);

    int validar_usuarionvl(String NUsuario);

    boolean registrar_usuario(String nombre, String Nidentifiacion, int TpIdentificacion, String contraseña);

    boolean finalizar_contrato(String Nidentifiacion);

    boolean cambiarDatos_usuario(String nombre, String Nidentifiacion, int TpIdentificacion, String contraseña);

    ArrayList<modeloUsuario> enlistar_usuarios() throws ClassNotFoundException, SQLException;
}
