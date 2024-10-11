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
            sql.append("SELECT * FROM CURSO");
            ResultSet resultSet = stmt.executeQuery(sql.toString());

            while (resultSet.next()) {
                Curso curso = new Curso();
                curso.setId(resultSet.getLong("id"));
                curso.setNome(resultSet.getString("nome"));
                curso.setEmenta(resultSet.getString("ementa"));
                cursos.add(curso);
            }
        }
        return cursos;
    }
}
