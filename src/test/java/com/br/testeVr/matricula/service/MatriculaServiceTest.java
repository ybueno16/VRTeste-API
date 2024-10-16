package com.br.testeVr.matricula.service;

import com.br.testeVr.matricula.model.Matricula;
import com.br.testeVr.matricula.repository.MatriculaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MatriculaServiceTest {
    @Mock
    private MatriculaRepository matriculaRepository;

    @InjectMocks
    private MatriculaService matriculaService;

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
    public void testCadastrarMatricula() throws Exception {
        matriculaRepository.cadastrarMatricula(matricula);
        verify(matriculaRepository,times(1)).cadastrarMatricula(matricula);
    }

    @Test
    public void testRemoverMatricula() throws Exception {
        doNothing().when(matriculaRepository).removerMatricula(any(Long.class));
        matriculaService.removerMatricula(1L);
        verify(matriculaRepository).removerMatricula(1L);
    }

    @Test
    public void testGetMatriculas() throws Exception {
        matriculaRepository.getMatriculas();
        verify(matriculaRepository).getMatriculas();
    }


}