package com.br.testeVr.aluno.repository.impl;

import com.br.testeVr.TesteVrApplication;
import com.br.testeVr.aluno.model.Aluno;
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
class AlunoRepositoryImplITestIntegration {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AlunoRepositoryImpl repository;

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    );


    @Test
    void cadastrarAluno() throws SQLException {
        Aluno aluno = new Aluno();
        aluno.setNome("Teste");
        repository.cadastrarAlunos(aluno);

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM aluno WHERE nome = 'Teste'")) {
            assertTrue(rs.next());
            assertNotNull(rs.getString("nome"));
        }
    }

    @Test
    void removerAluno() throws Exception {
        Aluno aluno = new Aluno();
        aluno.setNome("Teste");
        repository.cadastrarAlunos(aluno);

        repository.removerAluno(aluno.getId());

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM aluno WHERE id = " + aluno.getId())) {
            assertFalse(rs.next());
        }
    }

}