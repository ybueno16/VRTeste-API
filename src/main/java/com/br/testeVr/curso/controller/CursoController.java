package com.br.testeVr.curso.controller;

import com.br.testeVr.config.Global;
import com.br.testeVr.config.ResponseEntity.DefaultResponseEntity;
import com.br.testeVr.config.ResponseEntity.DefaultResponseEntityFactory;
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
}
