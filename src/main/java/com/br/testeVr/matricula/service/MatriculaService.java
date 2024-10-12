package com.br.testeVr.matricula.service;

import com.br.testeVr.matricula.model.Matricula;
import com.br.testeVr.matricula.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatriculaService {
    private MatriculaRepository matriculaRepository;

    @Autowired
    public MatriculaService(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    public void cadastrarMatricula(Matricula matricula) throws Exception {
        matriculaRepository.cadastrarMatricula(matricula);
    }

    public void removerMatricula(Long id) throws Exception {
        matriculaRepository.removerMatricula(id);
    }
}
