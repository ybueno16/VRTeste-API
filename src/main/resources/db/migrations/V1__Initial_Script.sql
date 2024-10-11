CREATE TABLE curso (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL,
    ementa TEXT NOT NULL
);

CREATE TABLE aluno (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE curso_aluno (
    id SERIAL PRIMARY KEY,
    id_aluno INT,
    id_curso INT,
    FOREIGN KEY (id_aluno) REFERENCES aluno(id),
    FOREIGN KEY (id_curso) REFERENCES curso(id)
);