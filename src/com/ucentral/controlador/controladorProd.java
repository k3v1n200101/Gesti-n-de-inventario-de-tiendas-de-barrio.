package com.ucentral.controlador;

import com.ucentral.Modelo.ProductoDAO;
import com.ucentral.Modelo.UsuarioDAO;
import com.ucentral.Vista.VistaAplicacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class controladorProd {
    public VistaAplicacion Vaplicacion;
    public  ProductoDAO IProducto = new ProductoDAO();
    public UsuarioDAO Udao = new UsuarioDAO();

    public controladorProd(VistaAplicacion vaplicacion) throws SQLException, ClassNotFoundException {
        Vaplicacion = vaplicacion;
        mostrar_Productos();
        Vaplicacion.ingresoDeMercanciaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog(null, "Ingresa el Nombre del producto que ingresa.", "Nombre de producto", JOptionPane.INFORMATION_MESSAGE);
                int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa la cantidad del producto.", "Cantidad de producto", JOptionPane.INFORMATION_MESSAGE));
                try {
                    IProducto.ingresar_mercancia(nombre, cantidad);
                    mostrar_Productos();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        Vaplicacion.modificarUnProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pruductID = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID actual del producto que desea modificar.", "ID de producto", JOptionPane.INFORMATION_MESSAGE));
                String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del producto que desea modificar.", "Nombre de producto", JOptionPane.INFORMATION_MESSAGE);
                int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la nueva cantidad.", "Cantidad de producto", JOptionPane.INFORMATION_MESSAGE));
                try {
                    IProducto.cambiarDatos_producto(pruductID, nombre, cantidad);
                    mostrar_Productos();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        Vaplicacion.eliminacionDeMercanciaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int productID = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del producto que desea eliminar.", "Eliminar un producto", JOptionPane.INFORMATION_MESSAGE));
                if(IProducto.acabar_existencias(productID)){
                    vaplicacion.llenar(IProducto.enlistar_Productos());
                }
            }
        });
    }

    public void mostrar_Productos() throws SQLException, ClassNotFoundException {
        Vaplicacion.llenar(IProducto.enlistar_Productos());
    }
}
