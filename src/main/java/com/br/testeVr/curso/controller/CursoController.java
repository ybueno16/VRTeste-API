package com.br.testeVr.curso.controller;

import com.br.testeVr.config.Global;
import com.br.testeVr.curso.model.Curso;
import com.br.testeVr.curso.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Curso> getCursos() throws Exception {
        return cursoService.getCursos();

    }
}
