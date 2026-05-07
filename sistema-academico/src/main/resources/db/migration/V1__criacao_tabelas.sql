-- =============================================================
-- V1: Criação das tabelas do Sistema Acadêmico
-- Schema: academico
-- =============================================================

CREATE SCHEMA IF NOT EXISTS academico;

-- Tabela de Período Letivo
CREATE TABLE IF NOT EXISTS academico.pel_periodo_letivo (
    pel_nr_id       SERIAL PRIMARY KEY,
    pel_nr_ano      INTEGER NOT NULL,
    pel_nr_semestre INTEGER NOT NULL,
    pel_tx_descricao VARCHAR(50),
    pel_tx_status   CHAR(1) NOT NULL DEFAULT 'A'
);

-- Tabela de Usuário
CREATE TABLE IF NOT EXISTS academico.usu_usuario (
    usu_nr_id       SERIAL PRIMARY KEY,
    usu_tx_nome     VARCHAR(150) NOT NULL,
    usu_tx_email    VARCHAR(150) NOT NULL UNIQUE,
    usu_tx_login    VARCHAR(50) NOT NULL UNIQUE,
    usu_tx_senha    VARCHAR(255) NOT NULL,
    usu_tx_perfil   VARCHAR(20) NOT NULL,
    usu_dt_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usu_tx_status   CHAR(1) NOT NULL DEFAULT 'A'
);

-- Tabela de Curso
CREATE TABLE IF NOT EXISTS academico.cur_curso (
    cur_nr_id         SERIAL PRIMARY KEY,
    cur_tx_nome       VARCHAR(150) NOT NULL,
    cur_tx_codigo_suap VARCHAR(50),
    cur_tx_status     CHAR(1) NOT NULL DEFAULT 'A'
);

-- Tabela de Disciplina
CREATE TABLE IF NOT EXISTS academico.dis_disciplina (
    dis_nr_id            SERIAL PRIMARY KEY,
    dis_tx_nome          VARCHAR(150) NOT NULL,
    dis_tx_codigo        VARCHAR(20),
    dis_tx_codigo_suap   VARCHAR(50),
    dis_nr_carga_horaria INTEGER,
    dis_tx_status        CHAR(1) NOT NULL DEFAULT 'A'
);

-- Tabela de Professor
CREATE TABLE IF NOT EXISTS academico.pro_professor (
    pro_nr_id             SERIAL PRIMARY KEY,
    usu_nr_id             INTEGER NOT NULL REFERENCES academico.usu_usuario(usu_nr_id),
    pro_tx_matricula_siape VARCHAR(50),
    pro_tx_status         CHAR(1) NOT NULL DEFAULT 'A'
);

-- Tabela de Aluno
CREATE TABLE IF NOT EXISTS academico.alu_aluno (
    alu_nr_id       SERIAL PRIMARY KEY,
    usu_nr_id       INTEGER NOT NULL REFERENCES academico.usu_usuario(usu_nr_id),
    cur_nr_id       INTEGER REFERENCES academico.cur_curso(cur_nr_id),
    alu_tx_matricula VARCHAR(20) NOT NULL UNIQUE,
    alu_tx_status   CHAR(1) NOT NULL DEFAULT 'A'
);

-- Tabela de Turma
CREATE TABLE IF NOT EXISTS academico.tur_turma (
    tur_nr_id         SERIAL PRIMARY KEY,
    dis_nr_id         INTEGER NOT NULL REFERENCES academico.dis_disciplina(dis_nr_id),
    pro_nr_id         INTEGER REFERENCES academico.pro_professor(pro_nr_id),
    pel_nr_id         INTEGER NOT NULL REFERENCES academico.pel_periodo_letivo(pel_nr_id),
    tur_tx_descricao  VARCHAR(200) NOT NULL,
    tur_tx_codigo_suap VARCHAR(50),
    tur_tx_status     CHAR(1) NOT NULL DEFAULT 'A'
);

-- Tabela de Matrícula na Turma (entidade intermediária Aluno <-> Turma)
CREATE TABLE IF NOT EXISTS academico.mtr_matricula_turma (
    mtr_nr_id   SERIAL PRIMARY KEY,
    alu_nr_id   INTEGER NOT NULL REFERENCES academico.alu_aluno(alu_nr_id),
    tur_nr_id   INTEGER NOT NULL REFERENCES academico.tur_turma(tur_nr_id),
    mtr_tx_status CHAR(1) NOT NULL DEFAULT 'A'
);

-- Tabela de Chat da Turma
CREATE TABLE IF NOT EXISTS academico.cht_chat_turma (
    cht_nr_id     SERIAL PRIMARY KEY,
    tur_nr_id     INTEGER NOT NULL REFERENCES academico.tur_turma(tur_nr_id),
    cht_tx_titulo VARCHAR(150),
    cht_dt_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    cht_tx_status CHAR(1) NOT NULL DEFAULT 'A'
);

-- Tabela de Mensagens do Chat
CREATE TABLE IF NOT EXISTS academico.msg_chat_mensagem (
    msg_nr_id       SERIAL PRIMARY KEY,
    cht_nr_id       INTEGER NOT NULL REFERENCES academico.cht_chat_turma(cht_nr_id),
    usu_nr_id       INTEGER NOT NULL REFERENCES academico.usu_usuario(usu_nr_id),
    msg_tx_mensagem VARCHAR(1000) NOT NULL,
    msg_dt_envio    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    msg_tx_status   CHAR(1) NOT NULL DEFAULT 'A'
);

-- Tabela de Notas
CREATE TABLE IF NOT EXISTS academico.not_nota (
    not_nr_id       SERIAL PRIMARY KEY,
    mtr_nr_id       INTEGER NOT NULL REFERENCES academico.mtr_matricula_turma(mtr_nr_id),
    not_tx_descricao VARCHAR(100) NOT NULL,
    not_nr_valor    NUMERIC(5,2) NOT NULL,
    not_tx_status   CHAR(1) NOT NULL DEFAULT 'A'
);

-- Tabela de Faltas
CREATE TABLE IF NOT EXISTS academico.fal_falta (
    fal_nr_id         SERIAL PRIMARY KEY,
    mtr_nr_id         INTEGER NOT NULL REFERENCES academico.mtr_matricula_turma(mtr_nr_id),
    fal_dt_data       DATE NOT NULL,
    fal_nr_quantidade INTEGER NOT NULL DEFAULT 1,
    fal_tx_observacao VARCHAR(255),
    fal_tx_status     CHAR(1) NOT NULL DEFAULT 'A'
);
