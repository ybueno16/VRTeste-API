package com.br.testeVr.matricula.controller;

import com.br.testeVr.config.ResponseEntity.DefaultResponseEntity;
import com.br.testeVr.matricula.model.Matricula;
import com.br.testeVr.matricula.service.MatriculaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MatriculaControllerTest {

    @Mock
    private MatriculaService matriculaService;

    @InjectMocks
    private MatriculaController matriculaController;

    private Matricula matricula;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        matricula = new Matricula();
        Long id = 1L;
        Long idAluno = 1L;
        Long idCurso = 1L;
        matricula.setId(id);
        matricula.setIdAluno(idAluno);
        matricula.setIdCurso(idCurso);
    }

    @Test
    public void testGetMatriculas() throws Exception {
        ResponseEntity<?> response = matriculaController.getMatriculas();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetMatriculassComErro() throws Exception {

        when(matriculaService.getMatriculas()).thenThrow(SQLException.class);
        ResponseEntity<?> response = matriculaController.getMatriculas();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testCadastrarMatricula() throws Exception {
        doNothing().when(matriculaService).cadastrarMatricula(matricula);
        ResponseEntity<?> response = matriculaController.cadastrarMatricula(matricula);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCadastrarMatriculassComErro() throws Exception {
        doThrow(SQLException.class).when(matriculaService).cadastrarMatricula(matricula);
        ResponseEntity<?> response = matriculaController.cadastrarMatricula(matricula);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }



    @Test
    public void testRemoverMatricula() throws Exception {
        doNothing().when(matriculaService).removerMatricula(matricula.getId());
        ResponseEntity<?> response = matriculaController.removerMatricula(matricula.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testRemoverMatriculassComErro() throws Exception {
        doThrow(SQLException.class).when(matriculaService).removerMatricula(1L);
        ResponseEntity<?> response = matriculaController.removerMatricula(1L);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}