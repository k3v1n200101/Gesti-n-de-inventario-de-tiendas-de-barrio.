package com.ucentral.Modelo;

public class modeloProducto {
    long Identificador;
    String Nombre;
    long Cantidad;

    public modeloProducto() {

    }

    public modeloProducto(long identificador, String nombre, long cantidad) {
        Identificador = identificador;
        Nombre = nombre;
        Cantidad = cantidad;
    }

    public long getIdentificador() {
        return Identificador;
    }

    public void setIdentificador(long identificador) {
        Identificador = identificador;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public long getCantidad() {
        return Cantidad;
    }

    public void setCantidad(long cantidad) {
        Cantidad = cantidad;
    }
}
