SET search_path = public;

-- 1. Tabela de Professores
CREATE TABLE IF NOT EXISTS professor(
    email VARCHAR(35) NOT NULL,
    nome VARCHAR(45) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    coordenador BOOL NOT NULL DEFAULT FALSE,
    PRIMARY KEY (email)
);

-- 2. Tabela de Matérias
CREATE TABLE IF NOT EXISTS materia(
    sigla VARCHAR(10) NOT NULL,
    titulo VARCHAR(45) NOT NULL,
    carga_horaria INT NOT NULL,
    email_professor VARCHAR(35) NOT NULL,

    PRIMARY KEY(sigla),
    CONSTRAINT fk_materia_professor FOREIGN KEY(email_professor) REFERENCES professor(email) ON DELETE RESTRICT
);

-- 3. Tabela de Tópicos (Conteúdo Programático)
CREATE TABLE IF NOT EXISTS topico(
    id BIGSERIAL,
    titulo VARCHAR(255) NOT NULL,
    aulas_minimas INT NOT NULL,
    aulas_maximas INT NOT NULL,
    prova BOOL DEFAULT FALSE,
    sigla_materia VARCHAR(10) NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT fk_topico_materia FOREIGN KEY(sigla_materia) REFERENCES materia(sigla) ON DELETE CASCADE
);

-- 4. Tabela de Slots de Aula da Semana (Horários padrão escolhidos nos CheckBoxes)
CREATE TABLE IF NOT EXISTS aula(
    dia_da_semana VARCHAR(25) NOT NULL,
    horario TIME NOT NULL,
    PRIMARY KEY (dia_da_semana, horario)
);

-- 5. Tabela de Calendário Diário (Motor Cronológico)
CREATE TABLE IF NOT EXISTS dia(
    data DATE NOT NULL,
    disponivel_prova BOOL NOT NULL DEFAULT TRUE,
    PRIMARY KEY (data)
);

-- 6. Tabela do Planejamento de Aulas Definitivo (Resultado do algoritmo)
CREATE TABLE IF NOT EXISTS aula_planejada(
    dia_da_semana VARCHAR(25) NOT NULL,
    horario TIME NOT NULL,
    data DATE NOT NULL,
    id_topico BIGINT,

    PRIMARY KEY (dia_da_semana, horario, data),
    CONSTRAINT fk_dia_da_semana_horario_possui FOREIGN KEY (dia_da_semana, horario) REFERENCES aula(dia_da_semana, horario) ON DELETE CASCADE,
    CONSTRAINT fk_titulo_topico_possui FOREIGN KEY (id_topico) REFERENCES topico(id) ON DELETE CASCADE,
    CONSTRAINT fk_data_dia_letivo FOREIGN KEY (data) REFERENCES dia(data) ON DELETE CASCADE -- Regra: Amarra com o calendário
);