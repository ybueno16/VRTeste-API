package com.br.testeVr.matricula.repository;

import com.br.testeVr.matricula.DTO.MatriculaInfo;
import com.br.testeVr.matricula.model.Matricula;

import java.util.List;

public interface MatriculaRepository {

    List<MatriculaInfo> getMatriculas() throws Exception;
    void cadastrarMatricula(Matricula matricula) throws Exception;
    void removerMatricula(Long id) throws Exception;
}
