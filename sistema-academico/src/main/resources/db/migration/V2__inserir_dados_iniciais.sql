INSERT INTO academico.pel_periodo_letivo (pel_nr_ano, pel_nr_semestre, pel_tx_descricao, pel_tx_status)
VALUES (2026, 1, '2026.1', 'A');

INSERT INTO academico.cur_curso (cur_tx_nome, cur_tx_codigo_suap, cur_tx_status)
VALUES ('Sistemas de Informação', 'SI-001', 'A');

INSERT INTO academico.usu_usuario (usu_tx_nome, usu_tx_email, usu_tx_login, usu_tx_senha, usu_tx_perfil, usu_tx_status)
VALUES ('Coordenador Geral', 'admin@campus.edu.br', 'admin', '123456', 'COORDENADOR', 'A');

INSERT INTO academico.usu_usuario (usu_tx_nome, usu_tx_email, usu_tx_login, usu_tx_senha, usu_tx_perfil, usu_tx_status)
VALUES ('Professor Carlos', 'carlos@campus.edu.br', 'carlos', '123456', 'PROFESSOR', 'A');

INSERT INTO academico.usu_usuario (usu_tx_nome, usu_tx_email, usu_tx_login, usu_tx_senha, usu_tx_perfil, usu_tx_status)
VALUES ('Aluno Exemplo', 'aluno@campus.edu.br', 'aluno1', '123456', 'ALUNO', 'A');

-- Inserindo o Professor (Vinculado ao usuário ID 2)
INSERT INTO academico.pro_professor (usu_nr_id, pro_tx_matricula_siape, pro_tx_status)
VALUES (2, 'SIAPE999888', 'A');

-- Inserindo o Aluno (Vinculado ao usuário ID 3 e Curso ID 1)
INSERT INTO academico.alu_aluno (usu_nr_id, cur_nr_id, alu_tx_matricula, alu_tx_status)
VALUES (3, 1, '20261001', 'A');

INSERT INTO academico.dis_disciplina (dis_tx_nome, dis_tx_codigo, dis_nr_carga_horaria, dis_tx_status)
VALUES ('Estrutura de Dados', 'ED101', 60, 'A');

-- Inserindo uma Turma (Disciplina 1, Professor 1, Periodo 1)
INSERT INTO academico.tur_turma (dis_nr_id, pro_nr_id, pel_nr_id, tur_tx_descricao, tur_tx_status)
VALUES (1, 1, 1, 'Turma A - Estrutura de Dados', 'A');