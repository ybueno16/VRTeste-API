package com.br.testeVr.aluno.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class AlunoTest {

    private Aluno aluno;

    @BeforeEach
    void setUp() {
        aluno = new Aluno();
        Long id = 1L;
        String nome = "Teste";
        aluno.setId(id);
        aluno.setNome(nome);
    }

    @Test
    void getId() {
        Long id = 2L;
        aluno.setId(id);
        assertEquals(id, aluno.getId());
    }

    @Test
    void setId() {
        Long id = 3L;
        aluno.setId(id);
        assertEquals(id, aluno.getId());
    }

    @Test
    void getNome() {
        String nome = "Novo Nome";
        aluno.setNome(nome);
        assertEquals(nome, aluno.getNome());
    }

    @Test
    void setNome() {
        String nome = "Nome Alterado";
        aluno.setNome(nome);
        assertEquals(nome, aluno.getNome());
    }
}