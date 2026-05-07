-- =============================================================
-- V3: Inserção de turma extra, chat e matrícula do aluno
-- =============================================================

-- Inserindo disciplina extra
INSERT INTO academico.dis_disciplina (dis_tx_nome, dis_tx_codigo, dis_tx_codigo_suap, dis_nr_carga_horaria, dis_tx_status)
VALUES ('Programação Web', 'PW01', 'SUAP-PW01', 60, 'A');

-- Inserindo turma extra
INSERT INTO academico.tur_turma (dis_nr_id, pro_nr_id, pel_nr_id, tur_tx_descricao, tur_tx_codigo_suap, tur_tx_status)
VALUES (2, 1, 1, 'Turma de Programação Web 2026.1', 'SUAP-TURMA-01', 'A');

-- Inserindo chat para a turma 1
INSERT INTO academico.cht_chat_turma (tur_nr_id, cht_tx_titulo, cht_dt_criacao, cht_tx_status)
VALUES (1, 'Chat Geral - Estrutura de Dados', CURRENT_TIMESTAMP, 'A');

-- Inserindo chat para a turma 2
INSERT INTO academico.cht_chat_turma (tur_nr_id, cht_tx_titulo, cht_dt_criacao, cht_tx_status)
VALUES (2, 'Chat Geral - Programação Web', CURRENT_TIMESTAMP, 'A');

-- Matriculando o aluno 1 nas turmas
INSERT INTO academico.mtr_matricula_turma (alu_nr_id, tur_nr_id, mtr_tx_status)
VALUES (1, 1, 'A');

INSERT INTO academico.mtr_matricula_turma (alu_nr_id, tur_nr_id, mtr_tx_status)
VALUES (1, 2, 'A');