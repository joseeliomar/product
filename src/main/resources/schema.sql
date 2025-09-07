-- ATENÇÃO: O banco de dados 'product' deve ser criado manualmente antes de rodar este script.
-- Exemplo: CREATE DATABASE product;

CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price NUMERIC(19,2) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);