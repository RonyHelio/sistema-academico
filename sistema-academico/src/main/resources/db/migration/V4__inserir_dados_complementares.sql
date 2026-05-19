-- Inserir Período Letivo
INSERT INTO academico.pel_periodo_letivo (pel_nr_ano, pel_nr_semestre, pel_tx_descricao, pel_tx_status)
VALUES (2025, 2, '2025.2', 'I');

-- Inserir Novos Usuários (IDs 4, 5 e 6)
INSERT INTO academico.usu_usuario (usu_tx_nome, usu_tx_email, usu_tx_login, usu_tx_senha, usu_tx_perfil, usu_tx_status)
VALUES ('Professora Maria', 'maria@campus.edu.br', 'maria', '123456', 'PROFESSOR', 'A');

INSERT INTO academico.usu_usuario (usu_tx_nome, usu_tx_email, usu_tx_login, usu_tx_senha, usu_tx_perfil, usu_tx_status)
VALUES ('Joao Silva', 'joao@campus.edu.br', 'joao', '123456', 'ALUNO', 'A');

INSERT INTO academico.usu_usuario (usu_tx_nome, usu_tx_email, usu_tx_login, usu_tx_senha, usu_tx_perfil, usu_tx_status)
VALUES ('Ana Souza', 'ana@campus.edu.br', 'ana', '123456', 'ALUNO', 'A');

-- Inserir novo Professor (ID 2, referenciando o usuário 4)
INSERT INTO academico.pro_professor (usu_nr_id, pro_tx_matricula_siape, pro_tx_status)
VALUES (4, 'SIAPE777666', 'A');

-- Inserir novos Alunos (IDs 2 e 3, referenciando os usuários 5 e 6)
INSERT INTO academico.alu_aluno (usu_nr_id, cur_nr_id, alu_tx_matricula, alu_tx_status)
VALUES (5, 1, '20261002', 'A');

INSERT INTO academico.alu_aluno (usu_nr_id, cur_nr_id, alu_tx_matricula, alu_tx_status)
VALUES (6, 1, '20261003', 'A');

-- Inserir nova Disciplina (ID 3)
INSERT INTO academico.dis_disciplina (dis_tx_nome, dis_tx_codigo, dis_tx_codigo_suap, dis_nr_carga_horaria, dis_tx_status)
VALUES ('Banco de Dados', 'BD01', 'SUAP-BD01', 80, 'A');

-- Inserir novas Turmas (IDs 3 e 4)
INSERT INTO academico.tur_turma (dis_nr_id, pro_nr_id, pel_nr_id, tur_tx_descricao, tur_tx_codigo_suap, tur_tx_status)
VALUES (3, 2, 1, 'Turma A - Banco de Dados 2026.1', 'SUAP-TURMA-03', 'A');

INSERT INTO academico.tur_turma (dis_nr_id, pro_nr_id, pel_nr_id, tur_tx_descricao, tur_tx_codigo_suap, tur_tx_status)
VALUES (1, 1, 2, 'Turma A - Estrutura de Dados 2025.2', 'SUAP-TURMA-04', 'I');

-- Inserir novo Chat (ID 3)
INSERT INTO academico.cht_chat_turma (tur_nr_id, cht_tx_titulo, cht_dt_criacao, cht_tx_status)
VALUES (3, 'Chat Geral - Banco de Dados', CURRENT_TIMESTAMP, 'A');

-- Inserir Matrículas
INSERT INTO academico.mtr_matricula_turma (alu_nr_id, tur_nr_id, mtr_tx_status) VALUES (2, 1, 'A'); -- ID 3 (Joao em ED)
INSERT INTO academico.mtr_matricula_turma (alu_nr_id, tur_nr_id, mtr_tx_status) VALUES (2, 2, 'A'); -- ID 4 (Joao em Web)
INSERT INTO academico.mtr_matricula_turma (alu_nr_id, tur_nr_id, mtr_tx_status) VALUES (3, 1, 'A'); -- ID 5 (Ana em ED)
INSERT INTO academico.mtr_matricula_turma (alu_nr_id, tur_nr_id, mtr_tx_status) VALUES (3, 3, 'A'); -- ID 6 (Ana em BD)
INSERT INTO academico.mtr_matricula_turma (alu_nr_id, tur_nr_id, mtr_tx_status) VALUES (1, 3, 'A'); -- ID 7 (Aluno 1 em BD)
INSERT INTO academico.mtr_matricula_turma (alu_nr_id, tur_nr_id, mtr_tx_status) VALUES (1, 4, 'I'); -- ID 8 (Aluno 1 turma inativa)

-- Inserir Notas
INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (1, 'Prova 1', 7.50, 'A');
INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (1, 'Prova 2', 8.00, 'A');
INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (1, 'Trabalho Final', 9.00, 'A');
INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (2, 'Prova 1', 6.00, 'A');
INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (2, 'Prova 2', 7.50, 'A');
INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (2, 'Projeto Web', 8.50, 'A');
INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (3, 'Prova 1', 8.50, 'A');
INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (3, 'Prova 2', 9.00, 'A');
INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (3, 'Trabalho Final', 8.00, 'A');
INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (4, 'Prova 1', 5.50, 'A');
INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (4, 'Prova 2', 6.50, 'A');
INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (4, 'Projeto Web', 7.00, 'A');
INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (5, 'Prova 1', 9.00, 'A');
INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (5, 'Prova 2', 9.50, 'A');
INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (5, 'Trabalho Final', 10.00, 'A');
INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (6, 'Prova 1', 7.00, 'A');
INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (6, 'Prova 2', 8.00, 'A');
INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (7, 'Prova 1', 6.50, 'A');
INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (7, 'Prova 2', 7.00, 'A');
INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (8, 'Prova 1', 7.00, 'A');
INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (8, 'Prova 2', 8.50, 'A');
INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (8, 'Trabalho Final', 9.00, 'A');

-- Inserir Faltas
INSERT INTO academico.fal_falta (mtr_nr_id, fal_dt_data, fal_nr_quantidade, fal_tx_observacao, fal_tx_status) VALUES (1, '2026-03-10', 2, 'Ausencia justificada', 'A');
INSERT INTO academico.fal_falta (mtr_nr_id, fal_dt_data, fal_nr_quantidade, fal_tx_observacao, fal_tx_status) VALUES (1, '2026-04-05', 1, NULL, 'A');
INSERT INTO academico.fal_falta (mtr_nr_id, fal_dt_data, fal_nr_quantidade, fal_tx_observacao, fal_tx_status) VALUES (2, '2026-03-15', 1, NULL, 'A');
INSERT INTO academico.fal_falta (mtr_nr_id, fal_dt_data, fal_nr_quantidade, fal_tx_observacao, fal_tx_status) VALUES (3, '2026-02-20', 2, 'Atestado medico', 'A');
INSERT INTO academico.fal_falta (mtr_nr_id, fal_dt_data, fal_nr_quantidade, fal_tx_observacao, fal_tx_status) VALUES (3, '2026-03-12', 1, NULL, 'A');
INSERT INTO academico.fal_falta (mtr_nr_id, fal_dt_data, fal_nr_quantidade, fal_tx_observacao, fal_tx_status) VALUES (4, '2026-03-20', 1, 'Problema pessoal', 'A');
INSERT INTO academico.fal_falta (mtr_nr_id, fal_dt_data, fal_nr_quantidade, fal_tx_observacao, fal_tx_status) VALUES (6, '2026-03-05', 1, NULL, 'A');
INSERT INTO academico.fal_falta (mtr_nr_id, fal_dt_data, fal_nr_quantidade, fal_tx_observacao, fal_tx_status) VALUES (6, '2026-04-15', 2, 'Consulta medica', 'A');
INSERT INTO academico.fal_falta (mtr_nr_id, fal_dt_data, fal_nr_quantidade, fal_tx_observacao, fal_tx_status) VALUES (7, '2026-03-25', 1, NULL, 'A');
INSERT INTO academico.fal_falta (mtr_nr_id, fal_dt_data, fal_nr_quantidade, fal_tx_observacao, fal_tx_status) VALUES (8, '2025-09-15', 2, NULL, 'A');

-- Inserir Mensagens do Chat 1 (Estrutura de Dados)
INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status) VALUES (1, 2, 'Bem-vindos a disciplina de Estrutura de Dados!', '2026-02-10 08:00:00', 'A');
INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status) VALUES (1, 3, 'Obrigado, professor! Quando sera a primeira prova?', '2026-02-10 09:15:00', 'A');
INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status) VALUES (1, 2, 'A primeira prova sera na semana 8.', '2026-02-10 10:30:00', 'A');
INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status) VALUES (1, 5, 'Professor, o trabalho pode ser feito em dupla?', '2026-02-12 14:00:00', 'A');
INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status) VALUES (1, 2, 'Sim, pode ser em dupla ou individual.', '2026-02-12 15:00:00', 'A');
INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status) VALUES (1, 3, 'Mensagem removida pelo autor.', '2026-02-13 08:00:00', 'I');

-- Inserir Mensagens do Chat 2 (Programação Web)
INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status) VALUES (2, 2, 'Ola turma! Nosso projeto web sera desenvolvido ao longo do semestre.', '2026-02-11 08:00:00', 'A');
INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status) VALUES (2, 3, 'Professor, podemos usar React?', '2026-02-11 10:00:00', 'A');
INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status) VALUES (2, 2, 'Sim, React ou Angular sao permitidos.', '2026-02-11 11:30:00', 'A');
INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status) VALUES (2, 5, 'Teste de mensagem inativa.', '2026-02-14 09:00:00', 'I');

-- Inserir Mensagens do Chat 3 (Banco de Dados)
INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status) VALUES (3, 4, 'Bem-vindos ao chat de Banco de Dados!', '2026-02-12 08:00:00', 'A');
INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status) VALUES (3, 6, 'Bom dia, professora! Estou animada para a disciplina.', '2026-02-12 09:00:00', 'A');
INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status) VALUES (3, 4, 'Vamos trabalhar bastante com SQL neste semestre.', '2026-02-12 10:00:00', 'A');
