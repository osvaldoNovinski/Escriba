CREATE TABLE IF NOT EXISTS cartorio_atribuicoes (
    cartorio_id INT,
    atribuicao_id VARCHAR(20),
    PRIMARY KEY (cartorio_id, atribuicao_id),
    FOREIGN KEY (cartorio_id) REFERENCES cartorios(id),
    FOREIGN KEY (atribuicao_id) REFERENCES atribuicao_cartorio(id)
);
