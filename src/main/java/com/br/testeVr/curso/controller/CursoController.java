package com.br.testeVr.curso.controller;

import com.br.testeVr.config.Global;
    import com.br.testeVr.config.ResponseEntity.DefaultResponseEntityFactory;
import com.br.testeVr.config.swagger.DefaultOperation;
import com.br.testeVr.curso.model.Curso;
import com.br.testeVr.curso.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(Global.API_URL + "/cursos")
public class CursoController {

    private final CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @DefaultOperation(summary = "Listar", description = "Listar Cursos", tags = {"Cursos"})
    @GetMapping
    public ResponseEntity<?> getCursos() throws Exception {
        try {
            List<Curso> cursos = this.cursoService.getCursos();
            return DefaultResponseEntityFactory.create(
                    "Cursos recuperados com sucesso!",
                    cursos,
                    HttpStatus.OK
            );
        } catch (SQLException e) {
            return DefaultResponseEntityFactory.create(
                    "Erro ao recuperar os Cursos!",
                    Collections.emptyList(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DefaultOperation(summary = "Cadastrar", description = "Cadastrar Cursos", tags = {"Cursos"})
    @PostMapping
    public ResponseEntity<?> cadastrarCurso(@RequestBody Curso curso) throws Exception {
        try {
            this.cursoService.cadastrarCurso(curso);
            return DefaultResponseEntityFactory.create(
                    "Cursos inseridos com sucesso!",
                    curso,
                    HttpStatus.OK
            );
        } catch (SQLException e) {
            return DefaultResponseEntityFactory.create(
                    "Erro ao inserir os Cursos!",
                    Collections.emptyList(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DefaultOperation(summary = "Editar", description = "Editar Cursos", tags = {"Cursos"})
    @PutMapping
    public ResponseEntity<?> alterarCurso(@RequestBody Curso curso) throws Exception {
        try {
            this.cursoService.alterarCurso(curso);
            return DefaultResponseEntityFactory.create(
                    "Cursos alterados com sucesso!",
                    curso,
                    HttpStatus.OK
            );
        } catch (SQLException e) {
            return DefaultResponseEntityFactory.create(
                    "Erro ao alterar os Cursos!",
                    Collections.emptyList(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    @DefaultOperation(summary = "Remover", description = "Remover Cursos", tags = {"Cursos"})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerCurso(@PathVariable("id") Long id) throws Exception {
        try {
            this.cursoService.removerCurso(id);
            return DefaultResponseEntityFactory.create(
                    "Cursos removido com sucesso!",
                    id,
                    HttpStatus.OK
            );
        } catch (SQLException e) {
            return DefaultResponseEntityFactory.create(
                    "Erro ao remover os Cursos!",
                    Collections.emptyList(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
