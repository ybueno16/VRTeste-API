package com.br.testeVr.curso.repository;

import com.br.testeVr.curso.model.Curso;

import java.util.List;

public interface CursoRepository {
    public List<Curso> getCursos() throws Exception;
    public void cadastrarCurso(Curso curso) throws Exception;
}
