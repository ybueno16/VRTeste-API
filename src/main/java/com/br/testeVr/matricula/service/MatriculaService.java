package com.br.testeVr.matricula.service;

import com.br.testeVr.matricula.model.Matricula;
import com.br.testeVr.matricula.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaService {
    private MatriculaRepository matriculaRepository;

    @Autowired
    public MatriculaService(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }


    public List<Matricula> getMatriculas() throws Exception {
        return matriculaRepository.getMatriculas();
    }

    public void cadastrarMatricula(Matricula matricula) throws Exception {
        matriculaRepository.cadastrarMatricula(matricula);
    }

    public void removerMatricula(Long id) throws Exception {
        matriculaRepository.removerMatricula(id);
    }
}
