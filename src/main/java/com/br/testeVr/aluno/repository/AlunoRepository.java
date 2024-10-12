package com.br.testeVr.aluno.repository;

import com.br.testeVr.aluno.model.Aluno;

import java.sql.SQLException;
import java.util.List;

public interface AlunoRepository {
    List<Aluno> getAlunos() throws SQLException;
    void cadastrarAlunos(Aluno aluno) throws SQLException;

}
