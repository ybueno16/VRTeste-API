package com.br.testeVr.curso.repository;

import com.br.testeVr.curso.model.Curso;

import java.util.List;

public interface CursoRepository {
    List<Curso> getCursos() throws Exception;
    void cadastrarCurso(Curso curso) throws Exception;
}
