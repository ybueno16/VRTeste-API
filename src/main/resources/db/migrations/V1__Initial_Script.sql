CREATE TABLE curso (
    codigo INT PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL,
    ementa TEXT NOT NULL
);

CREATE TABLE aluno (
    codigo INT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE curso_aluno (
    codigo INT PRIMARY KEY,
    codigo_aluno INT,
    codigo_curso INT,
    FOREIGN KEY (codigo_aluno) REFERENCES aluno(codigo),
    FOREIGN KEY (codigo_curso) REFERENCES curso(codigo)
);