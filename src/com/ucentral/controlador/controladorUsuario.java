package com.ucentral.controlador;

import com.ucentral.Modelo.UsuarioDAO;
import com.ucentral.Vista.Personal;
import com.ucentral.Vista.VistaAplicacion;
import com.ucentral.Vista.vistaInicio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class controladorUsuario {
    Personal empleados;
    UsuarioDAO Iusuario = new UsuarioDAO();
    vistaInicio Isesion;
    VistaAplicacion Vaplicacion;
    controladorProd CP;


    public controladorUsuario(Personal empleados, vistaInicio isesion, VistaAplicacion VAplicacion, controladorProd controladorProd) {
        super();
        this.CP = controladorProd;
        this.empleados = empleados;
        isesion = new vistaInicio();
        Isesion = isesion;
        Isesion.ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validarIngreso();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        this.Vaplicacion = VAplicacion;
        Vaplicacion.cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vaplicacion.setVisible(false);
                Isesion.setVisible(true);
            }
        });
        Vaplicacion.personalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vaplicacion.setVisible(false);
                try {
                    mostrar_empleados();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        Vaplicacion.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Vaplicacion.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Se cerrará la sesión.\n¿Esta seguro?", "Advertencia", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        Vaplicacion.setVisible(false);
                        Isesion.setVisible(true);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        this.empleados.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.empleados.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Volverá a la pantalla productos.\n¿Esta seguro?", "Advertencia", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        empleados.setVisible(false);
                        Vaplicacion.setVisible(true);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        this.empleados.crearNuevoUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tipo = 0;
                String nombre;
                String NumIdentificacion;
                String contraseña;
                nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del nuevo usuario.", "Nombre de usuario", JOptionPane.INFORMATION_MESSAGE);
                if (nombre != null) {
                    NumIdentificacion = JOptionPane.showInputDialog(null, "Ingrese el número de identificación del nuevo usuario.", "Número de identificación", JOptionPane.INFORMATION_MESSAGE);
                    if (NumIdentificacion != null) {
                        while (tipo < 1 || tipo > 4) {
                            try {
                                tipo = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el tipo de identificación:\n1. Cédula.\n2. Tarjeta de identidad.\n3. Nit.\n4. Pasaporte.", "Tipo de identificación", JOptionPane.INFORMATION_MESSAGE));
                            } catch (NumberFormatException numberFormatException) {
                                if (tipo == JOptionPane.CANCEL_OPTION) {
                                    continue;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Ingrese solo números por favor.", "ERROR", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            if (tipo == JOptionPane.CANCEL_OPTION) {
                                break;
                            } else if (tipo < 1 || tipo > 4) {
                                JOptionPane.showMessageDialog(null, "Valor incorrecto.\nPor favor intente de nuevo.", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        if (tipo != JOptionPane.CANCEL_OPTION) {
                            contraseña = JOptionPane.showInputDialog(null, "Ingrese la contraseña del nuevo usuario", "Contraseña de usuario", JOptionPane.INFORMATION_MESSAGE);
                            if (contraseña != null) {
                                try {
                                    Iusuario.registrar_usuario(nombre, NumIdentificacion, tipo, contraseña);
                                    mostrar_empleados();
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                } catch (ClassNotFoundException ex) {
                                    ex.printStackTrace();
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "No se ingreso el usuario.", "Operación cancelada", JOptionPane.CANCEL_OPTION);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No se ingreso el usuario.", "Operación cancelada", JOptionPane.CANCEL_OPTION);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ingreso el usuario.", "Operación cancelada", JOptionPane.CANCEL_OPTION);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se ingreso el usuario.", "Operación cancelada", JOptionPane.CANCEL_OPTION);
                }
            }
        });

        this.empleados.borrarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String NumIdentificacion;
                NumIdentificacion = JOptionPane.showInputDialog(null, "Ingrese el número de identificación del usuario que desea eliminar.", "Eliminar usuario", JOptionPane.WARNING_MESSAGE);
                if (NumIdentificacion != null) {
                    if(Iusuario.finalizar_contrato(NumIdentificacion)){
                        try {
                            empleados.llenar(Iusuario.enlistar_usuarios());
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "No se eliminó el usuario.", "Operación cancelada", JOptionPane.CANCEL_OPTION);
                }
            }
        });

        this.empleados.actualizarDatoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tipo = 0;
                String nombre;
                String NumIdentificacion;
                String contraseña;
                nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del usuario.", "Nombre de usuario", JOptionPane.INFORMATION_MESSAGE);
                if (nombre != null) {
                    NumIdentificacion = JOptionPane.showInputDialog(null, "Ingrese el nuevo número de identificación del usuario.", "Número de identificación", JOptionPane.INFORMATION_MESSAGE);
                    if (NumIdentificacion != null) {
                        while (tipo < 1 || tipo > 4) {
                            try {
                                tipo = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el tipo de identificación:\n1. Cédula.\n2. Tarjeta de identidad.\n3. Nit.\n4. Pasaporte.", "Tipo de identificación", JOptionPane.INFORMATION_MESSAGE));
                            } catch (NumberFormatException numberFormatException) {
                                if (tipo == JOptionPane.CANCEL_OPTION) {
                                    continue;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Ingrese solo números por favor.", "ERROR", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            if (tipo == JOptionPane.CANCEL_OPTION) {
                                break;
                            } else if (tipo < 1 || tipo > 4) {
                                JOptionPane.showMessageDialog(null, "Valor incorrecto.\nPor favor intente de nuevo.", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        if (tipo != JOptionPane.CANCEL_OPTION) {
                            contraseña = JOptionPane.showInputDialog(null, "Ingrese la nueva contraseña del usuario", "Contraseña de usuario", JOptionPane.INFORMATION_MESSAGE);
                            if (contraseña != null) {
                                try {
                                    Iusuario.registrar_usuario(nombre, NumIdentificacion, tipo, contraseña);
                                    mostrar_empleados();
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                } catch (ClassNotFoundException ex) {
                                    ex.printStackTrace();
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "No se modificó el usuario.", "Operación cancelada", JOptionPane.CANCEL_OPTION);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No se modificó el usuario.", "Operación cancelada", JOptionPane.CANCEL_OPTION);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se modificó el usuario.", "Operación cancelada", JOptionPane.CANCEL_OPTION);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se modificó el usuario.", "Operación cancelada", JOptionPane.CANCEL_OPTION);
                }
            }
        });

        this.empleados.volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    empleados.setVisible(false);
                    Vaplicacion.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        Inicio_sesion();
    }

    public void mostrar_empleados() throws SQLException, ClassNotFoundException {
        empleados.llenar(Iusuario.enlistar_usuarios());
        empleados.pack();
        empleados.setLocationRelativeTo(null);
        empleados.setVisible(true);
    }

    public void Inicio_sesion() {
        Isesion.pack();
        Isesion.setLocationRelativeTo(null);
        Isesion.setVisible(true);
    }

    public void validarIngreso() throws SQLException, ClassNotFoundException {
        if (Iusuario.validar_usuario(Isesion.getTxtUsuario(), Isesion.getTxtContraseña())) {
            int nivel = Iusuario.validar_usuarionvl(Isesion.getTxtUsuario());
            if (nivel == 2) {
                Vaplicacion.deshabilitarBotones();
                Vaplicacion.pack();
                Vaplicacion.setLocationRelativeTo(null);
                Isesion.setVisible(false);
                Vaplicacion.setVisible(true);
                CP.mostrar_Productos();

            } else if (nivel == 1) {
                Vaplicacion.habilitarBotones();
                Vaplicacion.pack();
                Vaplicacion.setLocationRelativeTo(null);
                Isesion.setVisible(false);
                Vaplicacion.setVisible(true);
                CP.mostrar_Productos();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Usuario no encontrado", JOptionPane.ERROR_MESSAGE);
        }
    }
}
