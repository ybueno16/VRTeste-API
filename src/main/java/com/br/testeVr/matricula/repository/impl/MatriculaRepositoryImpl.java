package com.br.testeVr.matricula.repository.impl;

import com.br.testeVr.matricula.model.Matricula;
import com.br.testeVr.matricula.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Repository
public class MatriculaRepositoryImpl implements MatriculaRepository {
    StringBuilder sql = null;
    private final DataSource dataSource;

    @Autowired
    public MatriculaRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }




    @Override
    public void cadastrarMatricula(Matricula matricula) throws Exception {
        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
            sql = new StringBuilder();
            sql.append("INSERT INTO curso_aluno (id_aluno, id_curso) VALUES (");
            sql.append(matricula.getIdAluno());
            sql.append(", ");
            sql.append(matricula.getIdCurso());
            sql.append(")");
            stmt.executeUpdate(sql.toString());
        }

    }

    @Override
    public void removerMatricula(Long id) throws Exception {

        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
            sql = new StringBuilder();
            sql.append("DELETE FROM curso_aluno WHERE id = ");
            sql.append(id);
            stmt.executeUpdate(sql.toString());
        }
    }
}
