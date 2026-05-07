INSERT INTO academico.dis_disciplina (dis_tx_nome, dis_tx_codigo, dis_tx_codigo_suap, dis_nr_carga_horaria, dis_tx_status)
VALUES ('Programação Web', 'PW01', 'SUAP-PW01', 60, 'A');

INSERT INTO academico.tur_turma (dis_nr_id, pro_nr_id, pel_nr_id, tur_tx_descricao, tur_tx_codigo_suap, tur_tx_status)
VALUES (1, 1, 1, 'Turma de Programação Web 2026.1', 'SUAP-TURMA-01', 'A');

INSERT INTO academico.cht_chat_turma (tur_nr_id, cht_tx_titulo, cht_tx_status)
VALUES (1, 'Chat Geral - Programação Web', 'A');