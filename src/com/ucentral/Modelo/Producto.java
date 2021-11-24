package com.ucentral.Modelo;

import java.util.ArrayList;

public interface Producto {
    boolean ingresar_mercancia(String nombre, int cantidad);

    boolean acabar_existencias(int identificacion);

    boolean cambiarDatos_producto(int Identificador, String nombre, int cantidad);

    ArrayList<modeloProducto> enlistar_Productos();
}
