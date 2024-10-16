package com.br.testeVr.matricula.repository.impl;

import com.br.testeVr.matricula.DTO.MatriculaInfo;
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
    public List<MatriculaInfo> getMatriculas() throws Exception {

        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
            sql = new StringBuilder();
            sql.append("SELECT ca.id, c.descricao AS nome_curso, c.id AS id_curso, a.nome AS nome_aluno, a.id AS id_aluno ");
            sql.append("FROM curso_aluno ca ");
            sql.append("INNER JOIN curso c ON ca.id_curso = c.id ");
            sql.append("INNER JOIN aluno a ON ca.id_aluno = a.id ");

            ResultSet resultSet = stmt.executeQuery(sql.toString());

            List<MatriculaInfo> matriculas = new ArrayList<>();

            while (resultSet.next()) {
                MatriculaInfo matricula = new MatriculaInfo();
                matricula.setId(resultSet.getLong("id"));
                matricula.setNomeCurso(resultSet.getString("nome_curso"));
                matricula.setNomeAluno(resultSet.getString("nome_aluno"));
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
            sql.append("WHERE a.id = ");
            sql.append(matricula.getIdAluno());
            sql.append(" AND c.id = ");
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
