package com.br.testeVr.matricula.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatriculaTest {

    private Matricula matricula;

    @BeforeEach
    public void setup() {
        matricula = new Matricula();
        Long id = 1L;
        Long idAluno = 1L;
        Long idCurso = 1L;
        matricula.setId(id);
        matricula.setIdAluno(idAluno);
        matricula.setIdCurso(idCurso);
    }

    @Test
    public void getId() {
        Long id = 2L;
        matricula.setId(id);
        assertEquals(id, matricula.getId());
    }

    @Test
    public void setId() {
        Long id = 3L;
        matricula.setId(id);
        assertEquals(id, matricula.getId());
    }

    @Test
    public void getIdAluno() {
        Long idAluno = 2L;
        matricula.setIdAluno(idAluno);
        assertEquals(idAluno, matricula.getIdAluno());
    }

    @Test
    public void setIdAluno() {
        Long idAluno = 3L;
        matricula.setIdAluno(idAluno);
        assertEquals(idAluno, matricula.getIdAluno());
    }

    @Test
    public void getIdCurso() {
        Long idCurso = 2L;
        matricula.setIdCurso(idCurso);
        assertEquals(idCurso, matricula.getIdCurso());
    }

    @Test
    public void setIdCurso() {
        Long idCurso = 3L;
        matricula.setIdCurso(idCurso);
        assertEquals(idCurso, matricula.getIdCurso());
    }


}