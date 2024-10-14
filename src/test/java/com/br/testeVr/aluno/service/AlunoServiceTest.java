package com.br.testeVr.aluno.service;

import com.br.testeVr.aluno.model.Aluno;
import com.br.testeVr.aluno.repository.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

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
        List<Aluno> alunos = new ArrayList<>();
        alunos.add(aluno);
        when(alunoRepository.getAlunos()).thenReturn(alunos);
        List<Aluno> alunosRetornados = alunoService.getAlunos();
        assertNotNull(alunosRetornados);
        assertEquals(1, alunosRetornados.size());
        assertEquals(aluno.getNome(), alunosRetornados.get(0).getNome());
    }

    @Test
    public void testCadastrarAlunos() throws Exception {
        alunoService.cadastrarAlunos(aluno);
        verify(alunoRepository, times(1)).cadastrarAlunos(aluno);
    }


    @Test
    public void testAlterarAluno() throws Exception {
        alunoService.alterarAluno(aluno);
        verify(alunoRepository, times(1)).alterarAluno(aluno);

    }

    @Test
    public void testRemoverAluno() throws Exception {
        alunoService.removerAluno(aluno.getId());
        verify(alunoRepository, times(1)).removerAluno(aluno.getId());
    }
}