package com.ucentral.controlador;

import com.ucentral.Vista.Personal;
import com.ucentral.Vista.VistaAplicacion;
import com.ucentral.Vista.vistaInicio;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Personal Prsonal = new Personal();// crud del personal
        VistaAplicacion vistaAplicacion = new VistaAplicacion();//inicio Productos
        vistaInicio VInicio = null;// login
        controladorProd CP = new controladorProd(vistaAplicacion);
        controladorUsuario CS = new controladorUsuario(Prsonal, VInicio, vistaAplicacion, CP);

    }
}
