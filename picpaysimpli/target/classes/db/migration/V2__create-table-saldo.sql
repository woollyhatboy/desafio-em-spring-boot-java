CREATE TABLE saldo (
                       id_usuario INT REFERENCES usuarios(id) ON DELETE CASCADE,
                       saldo NUMERIC(20, 2) NOT NULL DEFAULT 0,
                       PRIMARY KEY (id_usuario)
);