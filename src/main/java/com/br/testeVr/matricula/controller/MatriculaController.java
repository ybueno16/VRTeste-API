package com.br.testeVr.matricula.controller;

import com.br.testeVr.config.Global;
import com.br.testeVr.config.ResponseEntity.DefaultResponseEntityFactory;
import com.br.testeVr.config.swagger.DefaultOperation;
import com.br.testeVr.matricula.DTO.MatriculaInfo;
import com.br.testeVr.matricula.model.Matricula;
import com.br.testeVr.matricula.service.MatriculaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(Global.API_URL + "/matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @DefaultOperation(summary = "Listar", description = "LIstar Matriculas", tags = {"Matricula"})
    @GetMapping
    public ResponseEntity<?> getMatriculas() throws Exception {
        try {
            List<MatriculaInfo> matriculas = this.matriculaService.getMatriculas();
            return DefaultResponseEntityFactory.create(
                    "Matriculas recuperadas com sucesso!",
                    matriculas,
                    HttpStatus.OK
            );
        } catch (SQLException e) {
            return DefaultResponseEntityFactory.create(
                    "Erro ao recuperar as Matriculas!",
                    Collections.emptyList(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DefaultOperation(summary = "Cadastrar", description = "Cadastrar Matriculas", tags = {"Matriculas"})
    @PostMapping
    public ResponseEntity<?> cadastrarMatricula(@RequestBody Matricula matricula) throws Exception {
        try {
            this.matriculaService.cadastrarMatricula(matricula);
            return DefaultResponseEntityFactory.create(
                    "Matricula inserido com sucesso!",
                    matricula,
                    HttpStatus.OK
            );
        } catch (SQLException e) {
            return DefaultResponseEntityFactory.create(
                    "Erro ao inserir a Matricula!",
                    Collections.emptyList(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DefaultOperation(summary = "Remover", description = "Remover Matriculas", tags = {"Matriculas"})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerMatricula(@PathVariable("id") Long id) throws Exception {
        try {
            this.matriculaService.removerMatricula(id);
            return DefaultResponseEntityFactory.create(
                    "Matricula removido com sucesso!",
                    id,
                    HttpStatus.OK
            );
        } catch (SQLException e) {
            return DefaultResponseEntityFactory.create(
                    "Erro ao remover a Matricula!",
                    Collections.emptyList(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

}
