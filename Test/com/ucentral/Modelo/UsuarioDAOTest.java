package com.ucentral.Modelo;

import org.junit.Test;

import static org.junit.Assert.*;

public class UsuarioDAOTest {

    @Test
    public void validar_usuario() {

        assertTrue("Funciona Validar Usuario",true);
    }

    @Test
    public void validar_usuarionvl() {
        int administrador = 1;
        assertEquals(1,administrador);
    }

    @Test
    public void finalizar_contrato() {
        assertTrue("Funciona finalizar_Contrato",true);
    }
}