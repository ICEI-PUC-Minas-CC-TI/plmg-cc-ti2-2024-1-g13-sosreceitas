CREATE DATABASE SOSRECEITAS;
USE SOSRECEITAS;

-- Tabela de Usuários
CREATE TABLE Usuarios (
    usuario_id SERIAL PRIMARY KEY,
    nome_usuario VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    status_inscricao BOOLEAN DEFAULT FALSE
);

-- Tabela de Receitas
CREATE TABLE Receitas (
    receita_id SERIAL PRIMARY KEY,
    usuario_id INT REFERENCES Usuarios(usuario_id),
    titulo VARCHAR(255) NOT NULL,
    imagem BYTEA, 
    conteudo TEXT
);

-- Tabela de Assinaturas
CREATE TABLE Assinaturas (
    assinatura_id SERIAL PRIMARY KEY,
    usuario_id INT REFERENCES usuarios(usuario_id),
    data_inicio DATE,
    data_fim DATE,
    status VARCHAR(50)
);

-- Tabela de Mensagens do Chatbot
CREATE TABLE Mensagens_chatbot (
    usuario_id INT REFERENCES usuarios(usuario_id),
    conteudo_mensagem TEXT,
    imagem BYTEA

    CONSTRAINT fk_usuario_chat FOREIGN KEY (usuario_id) REFERENCES usuarios(usuario_id)
);

-- Tabela de Tags
CREATE TABLE Tags (
    tag_id SERIAL PRIMARY KEY,
    nome_tag VARCHAR(100) NOT NULL UNIQUE
);

-- Tabela de Relacionamento entre Receitas e Tags
CREATE TABLE Receita_tags (
    receita_id INT,
    tag_id INT,

    FOREIGN KEY (receita_id) REFERENCES Receitas(receita_id),
    FOREIGN KEY (tag_id) REFERENCES Tags(tag_id),
    PRIMARY KEY (receita_id, tag_id)
);
