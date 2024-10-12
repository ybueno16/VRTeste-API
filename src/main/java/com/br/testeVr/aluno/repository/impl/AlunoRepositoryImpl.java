package com.br.testeVr.aluno.repository.impl;

import com.br.testeVr.aluno.model.Aluno;
import com.br.testeVr.aluno.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AlunoRepositoryImpl implements AlunoRepository {
    StringBuilder sql = null;
    private final DataSource dataSource;

    @Autowired
    public AlunoRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Aluno> getAlunos() throws SQLException {
        List<Aluno> alunos = new ArrayList<>();
        try(Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()){

            sql = new StringBuilder();
            sql.append("SELECT a.id, a.nome FROM aluno a");
            ResultSet resultSet = stmt.executeQuery(sql.toString());

            while (resultSet.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(resultSet.getLong("id"));
                aluno.setNome(resultSet.getString("nome"));
                alunos.add(aluno);
            }
        }
        return alunos;
    }

    @Override
    public void cadastrarAlunos(Aluno aluno) throws SQLException {
        try(Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()){
            sql = new StringBuilder();
            sql.append("INSERT INTO aluno (nome) VALUES (");
            sql.append("'");
            sql.append(aluno.getNome());
            sql.append("')");
            stmt.executeUpdate(sql.toString());
        }
    }
}
