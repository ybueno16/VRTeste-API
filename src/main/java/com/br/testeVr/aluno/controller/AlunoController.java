package com.br.testeVr.aluno.controller;

import com.br.testeVr.aluno.model.Aluno;
import com.br.testeVr.aluno.service.AlunoService;
import com.br.testeVr.config.Global;
import com.br.testeVr.config.ResponseEntity.DefaultResponseEntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(Global.API_URL + "/alunos")
public class AlunoController {
    private final AlunoService alunoService;

    @Autowired
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public ResponseEntity<?> getAlunos() throws Exception {
        try {
            List<Aluno> alunos = this.alunoService.getAlunos();
            return DefaultResponseEntityFactory.create(
                    "Alunos recuperados com sucesso!",
                    alunos,
                    HttpStatus.OK
            );
        } catch (SQLException e) {
            return DefaultResponseEntityFactory.create(
                    "Erro ao recuperar os Alunos!",
                    Collections.emptyList(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrarAluno(@RequestBody Aluno aluno) throws Exception {
        try {
            this.alunoService.cadastrarAlunos(aluno);
            return DefaultResponseEntityFactory.create(
                    "Alunos inseridos com sucesso!",
                    aluno,
                    HttpStatus.OK
            );
        } catch (SQLException e) {
            return DefaultResponseEntityFactory.create(
                    "Erro ao inserir os Alunos!",
                    Collections.emptyList(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PutMapping
    public ResponseEntity<?> alterarAluno(@RequestBody Aluno aluno) throws Exception {
        try {
            this.alunoService.alterarAluno(aluno);
            return DefaultResponseEntityFactory.create(
                    "Alunos alterados com sucesso!",
                    aluno,
                    HttpStatus.OK
            );
        } catch (SQLException e) {
            return DefaultResponseEntityFactory.create(
                    "Erro ao atualizar dado de Aluno!",
                    Collections.emptyList(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerAluno (@PathVariable("id") Long id) throws Exception {
        try {

            this.alunoService.removerAluno(id);
            return DefaultResponseEntityFactory.create(
                    "Aluno removido com sucesso!",
                    id,
                    HttpStatus.OK
            );
        } catch (SQLException e) {
            return DefaultResponseEntityFactory.create(
                    "Erro ao remover Aluno!",
                    Collections.emptyList(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

}
