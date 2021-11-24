package com.ucentral.Vista;

import com.ucentral.Modelo.modeloProducto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class VistaAplicacion extends JFrame {
    public JButton personalButton;
    public JButton modificarUnProductoButton;//otro metodo
    public JButton eliminacionDeMercanciaButton;
    public JButton ingresoDeMercanciaButton;
    public JButton cerrarSesionButton;
    public DefaultTableModel modelo = new DefaultTableModel();
    private JTable table1;
    private JPanel Ventana;

    public VistaAplicacion() {
        setContentPane(Ventana);
        String[] titulos = {"Identificador", "Nombre", "Cantidad"};
        modelo = new DefaultTableModel(null, titulos);
        table1.setModel(modelo);
    }

    public void deshabilitarBotones() {
        personalButton.setEnabled(false);
        eliminacionDeMercanciaButton.setEnabled(false);
    }

    public void habilitarBotones() {
        personalButton.setEnabled(true);
        eliminacionDeMercanciaButton.setEnabled(true);
    }

    public void llenar(ArrayList<modeloProducto> producto) {
        DefaultTableModel modelTemp = new DefaultTableModel(null, new String[]{"Identificador", "Nombre", "Cantidad"});
        int i = 0;
        while (i != producto.size()) {
            Object[] fila = new Object[3];
            fila[0] = producto.get(i).getIdentificador();
            fila[1] = producto.get(i).getNombre();
            fila[2] = producto.get(i).getCantidad();
            modelTemp.addRow(fila);
            i++;
        }
        table1.setModel(modelTemp);
    }
}
