package com.br.testeVr.curso.service;

import com.br.testeVr.curso.model.Curso;
import com.br.testeVr.curso.repository.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CursoServiceTest {
    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoService cursoService;

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
        when(cursoRepository.getCursos()).thenReturn(cursos);
        List<Curso> cursosRetornados = cursoService.getCursos();
        assertNotNull(cursosRetornados);
        assertEquals(1, cursosRetornados.size());
        assertEquals(curso.getDescricao(), cursosRetornados.get(0).getDescricao());
    }

    @Test
    public void testCadastrarCurso() throws Exception {
        cursoService.cadastrarCurso(curso);
        verify(cursoRepository, times(1)).cadastrarCurso(curso);
    }

    @Test
    public void testAlterarCurso() throws Exception {
        cursoService.alterarCurso(curso);
        verify(cursoRepository, times(1)).alterarCurso(curso);
    }

    @Test
    public void testRemoverCurso() throws Exception {
        cursoService.removerCurso(curso.getId());
        verify(cursoRepository, times(1)).removerCurso(curso.getId());
    }
}