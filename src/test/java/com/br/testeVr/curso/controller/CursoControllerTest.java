package com.br.testeVr.curso.controller;

import com.br.testeVr.curso.model.Curso;
import com.br.testeVr.curso.service.CursoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CursoControllerTest {
    @Mock
    private CursoService cursoService;

    @InjectMocks
    private CursoController cursoController;

    private Curso curso;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        curso = new Curso();
        curso.setId(1L);
        curso.setDescricao("Teste");
    }

    @Test
    public void testGetCursos() throws Exception {
        List<Curso> cursos = new ArrayList<>();
        cursos.add(curso);
        when(cursoService.getCursos()).thenReturn(cursos);
        ResponseEntity<?> response = cursoController.getCursos();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetCursosComErro() throws Exception {
        when(cursoService.getCursos()).thenThrow(SQLException.class);
        ResponseEntity<?> response = cursoController.getCursos();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testCadastrarCursos() throws Exception {
        doNothing().when(cursoService).cadastrarCurso(curso);
        ResponseEntity<?> response = cursoController.cadastrarCurso(curso);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCadastrarCursosComErro() throws Exception {
        doThrow(SQLException.class).when(cursoService).cadastrarCurso(curso);
        ResponseEntity<?> response = cursoController.cadastrarCurso(curso);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


    @Test
    public void testAlterarCursos() throws Exception {
        doNothing().when(cursoService).alterarCurso(curso);
        ResponseEntity<?> response = cursoController.alterarCurso(curso);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    public void testAlterarCursosComErro() throws Exception {
        doThrow(SQLException.class).when(cursoService).alterarCurso(curso);
        ResponseEntity<?> response = cursoController.alterarCurso(curso);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


    @Test
    public void testRemoverCursos() throws Exception {
        doNothing().when(cursoService).removerCurso(curso.getId());
        ResponseEntity<?> response = cursoController.removerCurso(curso.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    public void testRemoverCursosComErro() throws Exception {
        doThrow(SQLException.class).when(cursoService).removerCurso(curso.getId());
        ResponseEntity<?> response = cursoController.removerCurso(curso.getId());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}