package com.ucentral.Modelo;

public class modeloUsuario {
    int id;
    String Cedula;
    String nombre;
    String contraseña;
    int nvl;

    public modeloUsuario() {

    }

    public modeloUsuario(String cedula, String nombre, String contraseña, int nvl) {
        Cedula = cedula;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.nvl = nvl;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getNvl() {
        return nvl;
    }

    public void setNvl(int nvl) {
        this.nvl = nvl;
    }
}
