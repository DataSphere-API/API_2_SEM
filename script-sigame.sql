CREATE TABLE IF NOT EXISTS professor (
    email VARCHAR(255) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    coordenador BOOL DEFAULT false,
    PRIMARY KEY (email)
);

CREATE TABLE IF NOT EXISTS materia (
    sigla VARCHAR(50) NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    carga_horaria INT NOT NULL,
    email_professor VARCHAR(255),
    PRIMARY KEY (sigla),
    CONSTRAINT fk_email_professor FOREIGN KEY (email_professor) REFERENCES professor(email) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS topico (
    id BIGSERIAL,
    titulo VARCHAR(255) NOT NULL,
    aulas_minimas INT NOT NULL,
    aulas_maximas INT NOT NULL,
    prova BOOL DEFAULT false,
    sigla_materia VARCHAR(50),
    PRIMARY KEY (id),
    CONSTRAINT fk_sigla_materia FOREIGN KEY (sigla_materia) REFERENCES materia(sigla) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS aula (
    dia_da_semana VARCHAR(25) NOT NULL,
    horario TIME NOT NULL,
    PRIMARY KEY (dia_da_semana, horario)
);

CREATE TABLE IF NOT EXISTS dia (
    data DATE NOT NULL,
    disponivel_prova BOOL NOT NULL DEFAULT true,
    titulo VARCHAR(255),
    descricao TEXT,
    letivo BOOL DEFAULT true,
    PRIMARY KEY (data)
);

CREATE TABLE IF NOT EXISTS aula_planejada (
    dia_da_semana VARCHAR(25) NOT NULL,
    horario TIME NOT NULL,
    data DATE NOT NULL,
    id_topico BIGINT,
    PRIMARY KEY (dia_da_semana, horario, data),
    CONSTRAINT fk_data_dia_letivo FOREIGN KEY (data) REFERENCES dia(data) ON DELETE CASCADE,
    CONSTRAINT fk_titulo_topico_possui FOREIGN KEY (id_topico) REFERENCES topico(id) ON DELETE CASCADE
);
