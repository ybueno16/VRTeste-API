package com.br.testeVr.matricula.repository;

import com.br.testeVr.matricula.model.Matricula;

public interface MatriculaRepository {
    void cadastrarMatricula(Matricula matricula) throws Exception;
    void removerMatricula(Long id) throws Exception;
}
