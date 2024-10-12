package com.br.testeVr.curso.service;

import com.br.testeVr.curso.model.Curso;
import com.br.testeVr.curso.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private CursoRepository cursoRepository;

    @Autowired
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> getCursos() throws Exception {
        return cursoRepository.getCursos();
    }

    public void cadastrarCurso(Curso curso) throws Exception {
        cursoRepository.cadastrarCurso(curso);
    }

    public void removerCurso(Long id) throws Exception {
        cursoRepository.removerCurso(id);
    }
}
