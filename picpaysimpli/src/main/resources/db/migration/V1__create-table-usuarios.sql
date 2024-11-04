CREATE TABLE usuarios (
                          id SERIAL PRIMARY KEY,
                          nome VARCHAR(255) NOT NULL,
                          email VARCHAR(255) UNIQUE NOT NULL,
                          senha VARCHAR(255) NOT NULL,
                          cpf_cnpj VARCHAR(18) UNIQUE NOT NULL,
                          lojista BOOLEAN NOT NULL DEFAULT FALSE
);