package com.ucentral.Vista;

import com.ucentral.Modelo.modeloUsuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Personal extends JFrame {
    public JButton volverButton;
    public DefaultTableModel modelo;
    public JButton crearNuevoUsuarioButton;
    public JButton actualizarDatoButton;
    public JButton borrarUsuarioButton;
    private JTable table1;
    private JPanel Ventana;

    public Personal() {
        setContentPane(Ventana);
        String[] titulos = {"Nombre", "Contraseña", "Cedula", "Nivel"};
        modelo = new DefaultTableModel(null, titulos);
        table1.setModel(modelo);

    }

    public void llenar(ArrayList<modeloUsuario> usuarios) {
        DefaultTableModel modelTemp = new DefaultTableModel(null, new String[]{"Nombre", "Contraseña", "Cedula", "Nivel"});
        int i = 0;
        while (i != usuarios.size()) {
            Object[] fila = new Object[4];
            fila[0] = usuarios.get(i).getNombre();
            fila[1] = usuarios.get(i).getContraseña();
            fila[2] = usuarios.get(i).getCedula();
            fila[3] = usuarios.get(i).getNvl();
            modelTemp.addRow(fila);
            i++;
        }
        table1.setModel(modelTemp);
    }
}
