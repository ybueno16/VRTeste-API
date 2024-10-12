package com.br.testeVr.aluno.service;

import com.br.testeVr.aluno.model.Aluno;
import com.br.testeVr.aluno.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private AlunoRepository alunoRepository;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<Aluno> getAlunos() throws Exception {
        return alunoRepository.getAlunos();
    }

    public void cadastrarAlunos(Aluno aluno) throws Exception {
        alunoRepository.cadastrarAlunos(aluno);
    }

    public void alterarAluno(Aluno aluno) throws Exception {
        alunoRepository.alterarAluno(aluno);
    }

    public void removerAluno(Long id) throws Exception {
        alunoRepository.removerAluno(id);
    }
}
