package com.br.testeVr.curso.repository;

import com.br.testeVr.curso.model.Curso;

import java.util.List;

public interface CursoRepository {
    List<Curso> getCursos() throws Exception;
    void cadastrarCurso(Curso curso) throws Exception;
    void alterarCurso(Curso curso) throws Exception;
    void removerCurso(Long id) throws Exception;
}
