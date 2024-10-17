package com.br.testeVr.curso.repository.impl;

import com.br.testeVr.TesteVrApplication;
import com.br.testeVr.aluno.model.Aluno;
import com.br.testeVr.aluno.repository.impl.AlunoRepositoryImpl;
import com.br.testeVr.curso.model.Curso;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;


@Testcontainers
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TesteVrApplication.class)
class CursoRepositoryImplTestIntegration {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CursoRepositoryImpl repository;

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    );


    @Test
    void cadastrarCurso() throws Exception {
        Curso curso = new Curso();
        curso.setDescricao("Teste");
        curso.setEmenta("Teste");
        repository.cadastrarCurso(curso);

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM curso WHERE descricao = 'Teste'")) {
            assertTrue(rs.next());
            assertNotNull(rs.getString("descricao"));
        }
    }

    @Test
    void removerAluno() throws Exception {
        Curso curso = new Curso();
        curso.setDescricao("Teste");
        curso.setEmenta("Teste");
        repository.cadastrarCurso(curso);

        repository.removerCurso(curso.getId());

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM aluno WHERE id = " + curso.getId())) {
            assertFalse(rs.next());
        }
    }

}

