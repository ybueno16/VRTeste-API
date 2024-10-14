package com.br.testeVr.aluno.controller;

import com.br.testeVr.aluno.model.Aluno;
import com.br.testeVr.aluno.service.AlunoService;
import com.br.testeVr.config.ResponseEntity.DefaultResponseEntityFactory;
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
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AlunoControllerTest {

    @Mock
    private AlunoService alunoService;

    @InjectMocks
    private AlunoController alunoController;

    private Aluno aluno;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("Yuri");
    }

    @Test
    public void testGetAlunos() throws Exception {
        List<Aluno> alunos = Arrays.asList(aluno);
        when(alunoService.getAlunos()).thenReturn(alunos);
        ResponseEntity<?> response = alunoController.getAlunos();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetAlunosComErro() throws Exception {
        when(alunoService.getAlunos()).thenThrow(SQLException.class);
        ResponseEntity<?> response = alunoController.getAlunos();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testCadastrarAluno() throws Exception {
        doNothing().when(alunoService).cadastrarAlunos(aluno);
        ResponseEntity<?> response = alunoController.cadastrarAluno(aluno);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCadastrarAlunoComErro() throws Exception {
        doThrow(SQLException.class).when(alunoService).cadastrarAlunos(aluno);
        ResponseEntity<?> response = alunoController.cadastrarAluno(aluno);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testAlterarAluno() throws Exception {
        doNothing().when(alunoService).alterarAluno(aluno);
        ResponseEntity<?> response = alunoController.alterarAluno(aluno);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testAlterarAlunoComErro() throws Exception {
        doThrow(SQLException.class).when(alunoService).alterarAluno(aluno);
        ResponseEntity<?> response = alunoController.alterarAluno(aluno);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testRemoverAluno() throws Exception {
        doNothing().when(alunoService).removerAluno(aluno.getId());
        ResponseEntity<?> response = alunoController.removerAluno(aluno.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testRemoverAlunoComErro() throws Exception {
        doThrow(SQLException.class).when(alunoService).removerAluno(aluno.getId());
        ResponseEntity<?> response = alunoController.removerAluno(aluno.getId());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}