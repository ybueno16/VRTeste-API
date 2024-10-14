package com.br.testeVr.curso.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CursoTest {

    @Test
    public void testGetId() {
        Curso curso = new Curso();
        curso.setId(1L);
        assertEquals(1L, curso.getId().longValue());
    }

    @Test
    public void testSetId() {
        Curso curso = new Curso();
        curso.setId(1L);
        assertNotNull(curso.getId());
    }

    @Test
    public void testGetDescricao() {
        Curso curso = new Curso();
        curso.setDescricao("Descricao do curso");
        assertEquals("Descricao do curso", curso.getDescricao());
    }

    @Test
    public void testSetDescricao() {
        Curso curso = new Curso();
        curso.setDescricao("Descricao do curso");
        assertNotNull(curso.getDescricao());
    }

    @Test
    public void testGetEmenta() {
        Curso curso = new Curso();
        curso.setEmenta("Ementa do curso");
        assertEquals("Ementa do curso", curso.getEmenta());
    }

    @Test
    public void testSetEmenta() {
        Curso curso = new Curso();
        curso.setEmenta("Ementa do curso");
        assertNotNull(curso.getEmenta());
    }

    @Test
    public void testConstrutorPadrao() {
        Curso curso = new Curso();
        assertNull(curso.getId());
        assertNull(curso.getDescricao());
        assertNull(curso.getEmenta());
    }
}