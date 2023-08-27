CREATE TABLE IF NOT EXISTS cartorios (
	 id INT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    observacao VARCHAR(250),
    situacao_id VARCHAR(20) NOT NULL,
    FOREIGN KEY (situacao_id) REFERENCES situacao_cartorio(id)
);