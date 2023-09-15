CREATE TABLE IF NOT EXISTS situacao_cartorio (
	 id VARCHAR(20) PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

INSERT INTO situacao_cartorio (id, nome) VALUES ('SIT_ATIVO', 'Ativo');
INSERT INTO situacao_cartorio (id, nome) VALUES ('SIT_BLOQUEADO', 'Bloqueado');

