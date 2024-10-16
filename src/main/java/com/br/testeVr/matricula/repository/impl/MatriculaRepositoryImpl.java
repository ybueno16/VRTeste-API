package com.br.testeVr.matricula.repository.impl;

import com.br.testeVr.matricula.model.Matricula;
import com.br.testeVr.matricula.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MatriculaRepositoryImpl implements MatriculaRepository {
    StringBuilder sql = null;
    private final DataSource dataSource;

    @Autowired
    public MatriculaRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public List<Matricula> getMatriculas() throws Exception {

        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
            sql = new StringBuilder();
            sql.append("SELECT id, id_aluno, id_curso ");
            sql.append("FROM curso_aluno");


            ResultSet resultSet = stmt.executeQuery(sql.toString());

            List<Matricula> matriculas = new ArrayList<>();

            while (resultSet.next()) {
                Matricula matricula = new Matricula();
                matricula.setId(resultSet.getLong("id"));
                matricula.setIdAluno(resultSet.getLong("id_aluno"));
                matricula.setIdCurso(resultSet.getLong("id_curso"));
                matriculas.add(matricula);
            }
            return matriculas;
        }

    }


    @Override
    public void cadastrarMatricula(Matricula matricula) throws Exception {
        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
            sql = new StringBuilder();
            sql.append("INSERT INTO curso_aluno (id_aluno, id_curso) ");
            sql.append("SELECT a.id, c.id ");
            sql.append("FROM aluno a, curso c ");
            sql.append("WHERE a.id IN (SELECT id FROM aluno WHERE id = ");
            sql.append(matricula.getIdAluno());
            sql.append(") AND c.id IN (SELECT id FROM curso WHERE id = ");
            sql.append(matricula.getIdCurso());

            stmt.executeUpdate(sql.toString());
        }
    }

    @Override
    public void removerMatricula(Long id) throws Exception {
        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
            sql = new StringBuilder();
            sql.append("DELETE ca ");
            sql.append("FROM curso_aluno ca ");
            sql.append("JOIN aluno a ON ca.id_aluno = a.id ");
            sql.append("JOIN curso c ON ca.id_curso = c.id ");
            sql.append("WHERE ca.id = ");
            sql.append(id);

            stmt.executeUpdate(sql.toString());
        }
    }
}
