package com.br.testeVr.curso.repository.impl;

import com.br.testeVr.curso.model.Curso;
import com.br.testeVr.curso.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CursoRepositoryImpl implements CursoRepository {
    StringBuilder sql = null;
    private final DataSource dataSource;

    @Autowired
    public CursoRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Curso> getCursos() throws Exception {
        List<Curso> cursos = new ArrayList<>();
        try(Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()){

            sql = new StringBuilder();
            sql.append("SELECT c.id, c.descricao, c.ementa  FROM curso c");
            ResultSet resultSet = stmt.executeQuery(sql.toString());

            while (resultSet.next()) {
                Curso curso = new Curso();
                curso.setId(resultSet.getLong("id"));
                curso.setDescricao(resultSet.getString("descricao"));
                curso.setEmenta(resultSet.getString("ementa"));
                cursos.add(curso);
            }
        }
        return cursos;
    }

    public void cadastrarCurso (Curso curso) throws Exception {
        try(Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()){
            sql = new StringBuilder();
            sql.append("INSERT INTO curso (descricao, ementa) VALUES (");
            sql.append("'");
            sql.append(curso.getDescricao());
            sql.append("', '");
            sql.append(curso.getEmenta());
            sql.append("')");
            stmt.executeUpdate(sql.toString());
        }
    }

    @Override
    public void alterarCurso(Curso curso) throws Exception {

        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
            sql = new StringBuilder();
            sql.append("UPDATE curso SET ");
            sql.append("descricao = '");
            sql.append(curso.getDescricao());
            sql.append("', ementa = '");
            sql.append(curso.getEmenta());
            sql.append("' WHERE id = ");
            sql.append(curso.getId());
            stmt.executeUpdate(sql.toString());
        }
    }

    private boolean verificaAlunoMatriculado(Long codigoCurso) throws Exception {

        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
            sql = new StringBuilder();
            sql.append("SELECT COUNT(*) as total FROM curso_aluno WHERE id_curso = ");
            sql.append(codigoCurso);
            ResultSet resultSet = stmt.executeQuery(sql.toString());
            return resultSet.next() && resultSet.getInt("total") > 0;
        }

    }

    @Override
    public void removerCurso(Long id) throws Exception {

        if(verificaAlunoMatriculado(id)) {
            throw new Exception("Curso possui alunos matriculados");
        }

        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
            sql = new StringBuilder();
            sql.append("DELETE FROM curso WHERE id = ");
            sql.append(id);
            stmt.executeUpdate(sql.toString());
        }
    }
}
