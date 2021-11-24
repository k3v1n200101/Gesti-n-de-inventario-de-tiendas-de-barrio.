package com.ucentral.Vista;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class vistaInicio extends JFrame {
    public JTextField TxtUsuario;
    public JPasswordField TxtContraseña;
    public JButton ingresarButton;
    public JPanel Ventana;
    int nivel = 0;

    public vistaInicio() {
        cerrar();
        setContentPane(Ventana);
        setResizable(false);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {
                TxtContraseña.setText("");
                TxtUsuario.setText("");
            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    public void cerrar() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Esta seguro de cerrar la aplicación?", "Advertencia", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        System.exit(0);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getTxtUsuario() {
        return TxtUsuario.getText();
    }

    public String getTxtContraseña() {
        return TxtContraseña.getText();
    }
}
