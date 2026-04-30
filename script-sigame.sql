CREATE TABLE IF NOT EXISTS aula(
	dia_da_semana VARCHAR(25) NOT NULL,
	horario TIME NOT NULL,
	PRIMARY KEY (dia_da_semana, horario)
	
);
CREATE TABLE IF NOT EXISTS topico(
	id BIGSERIAL,
	titulo VARCHAR(255) NOT NULL,
	aulas_necessarias INT NOT NULL,
    prova BOOL,
	PRIMARY KEY(id)
	
);
CREATE TABLE IF NOT EXISTS dia(
    data DATE NOT NULL,
    disponivel_prova BOOL NOT NULL,
    PRIMARY KEY (data)

);

CREATE TABLE IF NOT EXISTS aula_planejada(
	dia_da_semana VARCHAR(25) NOT NULL,
	horario TIME NOT NULL,
	data DATE NOT NULL,
	id_topico BIGINT,
	
	PRIMARY KEY (dia_da_semana, horario, data),
	CONSTRAINT fk_dia_da_semana_horario_possui FOREIGN KEY (dia_da_semana, horario) REFERENCES aula(dia_da_semana, horario) ON DELETE CASCADE,
	CONSTRAINT fk_titulo_topico_possui FOREIGN KEY (id_topico) REFERENCES topico(id) ON DELETE CASCADE,
    CONSTRAINT fk_data_possui FOREIGN KEY (data) REFERENCES dia(data) ON DELETE CASCADE
);

