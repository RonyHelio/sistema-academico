DO $$
DECLARE
    v_pel1_id    INTEGER;
    v_pel2_id    INTEGER;
    v_usu_maria  INTEGER;
    v_usu_joao   INTEGER;
    v_usu_ana    INTEGER;
    v_pro_maria  INTEGER;
    v_alu_joao   INTEGER;
    v_alu_ana    INTEGER;
    v_dis_bd     INTEGER;
    v_tur_bd     INTEGER;
    v_tur_hist   INTEGER;
    v_cht_bd     INTEGER;
    v_mtr3       INTEGER;
    v_mtr4       INTEGER;
    v_mtr5       INTEGER;
    v_mtr6       INTEGER;
    v_mtr7       INTEGER;
    v_mtr8       INTEGER;
    v_usu_carlos INTEGER;
    v_usu_aluno1 INTEGER;
    v_alu1_id    INTEGER;
    v_pro1_id    INTEGER;
    v_dis1_id    INTEGER;
    v_dis2_id    INTEGER;
    v_tur1_id    INTEGER;
    v_tur2_id    INTEGER;
    v_mtr1       INTEGER;
    v_mtr2       INTEGER;
    v_cht1       INTEGER;
    v_cht2       INTEGER;
BEGIN

    SELECT usu_nr_id INTO v_usu_carlos FROM academico.usu_usuario WHERE usu_tx_login = 'carlos';
    SELECT usu_nr_id INTO v_usu_aluno1 FROM academico.usu_usuario WHERE usu_tx_login = 'aluno1';
    SELECT alu_nr_id INTO v_alu1_id FROM academico.alu_aluno WHERE alu_tx_matricula = '20261001';
    SELECT pro_nr_id INTO v_pro1_id FROM academico.pro_professor WHERE usu_nr_id = v_usu_carlos;
    SELECT dis_nr_id INTO v_dis1_id FROM academico.dis_disciplina WHERE dis_tx_codigo = 'ED101';
    SELECT dis_nr_id INTO v_dis2_id FROM academico.dis_disciplina WHERE dis_tx_codigo = 'PW01';
    SELECT pel_nr_id INTO v_pel1_id FROM academico.pel_periodo_letivo WHERE pel_tx_descricao = '2026.1';

    SELECT t.tur_nr_id INTO v_tur1_id FROM academico.tur_turma t WHERE t.dis_nr_id = v_dis1_id AND t.pel_nr_id = v_pel1_id;
    SELECT t.tur_nr_id INTO v_tur2_id FROM academico.tur_turma t WHERE t.dis_nr_id = v_dis2_id AND t.pel_nr_id = v_pel1_id;

    SELECT mtr_nr_id INTO v_mtr1 FROM academico.mtr_matricula_turma WHERE alu_nr_id = v_alu1_id AND tur_nr_id = v_tur1_id;
    SELECT mtr_nr_id INTO v_mtr2 FROM academico.mtr_matricula_turma WHERE alu_nr_id = v_alu1_id AND tur_nr_id = v_tur2_id;

    SELECT cht_nr_id INTO v_cht1 FROM academico.cht_chat_turma WHERE tur_nr_id = v_tur1_id;
    SELECT cht_nr_id INTO v_cht2 FROM academico.cht_chat_turma WHERE tur_nr_id = v_tur2_id;

    IF v_usu_carlos IS NULL THEN RAISE EXCEPTION 'usuario carlos nao encontrado'; END IF;
    IF v_usu_aluno1 IS NULL THEN RAISE EXCEPTION 'usuario aluno1 nao encontrado'; END IF;
    IF v_alu1_id IS NULL THEN RAISE EXCEPTION 'aluno 20261001 nao encontrado'; END IF;
    IF v_pro1_id IS NULL THEN RAISE EXCEPTION 'professor de carlos nao encontrado'; END IF;
    IF v_dis1_id IS NULL THEN RAISE EXCEPTION 'disciplina ED101 nao encontrada'; END IF;
    IF v_dis2_id IS NULL THEN RAISE EXCEPTION 'disciplina PW01 nao encontrada'; END IF;
    IF v_pel1_id IS NULL THEN RAISE EXCEPTION 'periodo 2026.1 nao encontrado'; END IF;
    IF v_tur1_id IS NULL THEN RAISE EXCEPTION 'turma de ED101 nao encontrada'; END IF;
    IF v_tur2_id IS NULL THEN RAISE EXCEPTION 'turma de PW01 nao encontrada'; END IF;
    IF v_mtr1 IS NULL THEN RAISE EXCEPTION 'matricula aluno1 turma1 nao encontrada'; END IF;
    IF v_mtr2 IS NULL THEN RAISE EXCEPTION 'matricula aluno1 turma2 nao encontrada'; END IF;
    IF v_cht1 IS NULL THEN RAISE EXCEPTION 'chat turma1 nao encontrado'; END IF;
    IF v_cht2 IS NULL THEN RAISE EXCEPTION 'chat turma2 nao encontrado'; END IF;

    INSERT INTO academico.pel_periodo_letivo (pel_nr_ano, pel_nr_semestre, pel_tx_descricao, pel_tx_status)
    VALUES (2025, 2, '2025.2', 'I') RETURNING pel_nr_id INTO v_pel2_id;

    INSERT INTO academico.usu_usuario (usu_tx_nome, usu_tx_email, usu_tx_login, usu_tx_senha, usu_tx_perfil, usu_tx_status)
    VALUES ('Professora Maria', 'maria@campus.edu.br', 'maria', '123456', 'PROFESSOR', 'A') RETURNING usu_nr_id INTO v_usu_maria;

    INSERT INTO academico.usu_usuario (usu_tx_nome, usu_tx_email, usu_tx_login, usu_tx_senha, usu_tx_perfil, usu_tx_status)
    VALUES ('Joao Silva', 'joao@campus.edu.br', 'joao', '123456', 'ALUNO', 'A') RETURNING usu_nr_id INTO v_usu_joao;

    INSERT INTO academico.usu_usuario (usu_tx_nome, usu_tx_email, usu_tx_login, usu_tx_senha, usu_tx_perfil, usu_tx_status)
    VALUES ('Ana Souza', 'ana@campus.edu.br', 'ana', '123456', 'ALUNO', 'A') RETURNING usu_nr_id INTO v_usu_ana;

    INSERT INTO academico.pro_professor (usu_nr_id, pro_tx_matricula_siape, pro_tx_status)
    VALUES (v_usu_maria, 'SIAPE777666', 'A') RETURNING pro_nr_id INTO v_pro_maria;

    INSERT INTO academico.alu_aluno (usu_nr_id, cur_nr_id, alu_tx_matricula, alu_tx_status)
    VALUES (v_usu_joao, (SELECT cur_nr_id FROM academico.cur_curso LIMIT 1), '20261002', 'A') RETURNING alu_nr_id INTO v_alu_joao;

    INSERT INTO academico.alu_aluno (usu_nr_id, cur_nr_id, alu_tx_matricula, alu_tx_status)
    VALUES (v_usu_ana, (SELECT cur_nr_id FROM academico.cur_curso LIMIT 1), '20261003', 'A') RETURNING alu_nr_id INTO v_alu_ana;

    INSERT INTO academico.dis_disciplina (dis_tx_nome, dis_tx_codigo, dis_tx_codigo_suap, dis_nr_carga_horaria, dis_tx_status)
    VALUES ('Banco de Dados', 'BD01', 'SUAP-BD01', 80, 'A') RETURNING dis_nr_id INTO v_dis_bd;

    INSERT INTO academico.tur_turma (dis_nr_id, pro_nr_id, pel_nr_id, tur_tx_descricao, tur_tx_codigo_suap, tur_tx_status)
    VALUES (v_dis_bd, v_pro_maria, v_pel1_id, 'Turma A - Banco de Dados 2026.1', 'SUAP-TURMA-03', 'A') RETURNING tur_nr_id INTO v_tur_bd;

    INSERT INTO academico.tur_turma (dis_nr_id, pro_nr_id, pel_nr_id, tur_tx_descricao, tur_tx_codigo_suap, tur_tx_status)
    VALUES (v_dis1_id, v_pro1_id, v_pel2_id, 'Turma A - Estrutura de Dados 2025.2', 'SUAP-TURMA-04', 'I') RETURNING tur_nr_id INTO v_tur_hist;

    INSERT INTO academico.cht_chat_turma (tur_nr_id, cht_tx_titulo, cht_dt_criacao, cht_tx_status)
    VALUES (v_tur_bd, 'Chat Geral - Banco de Dados', CURRENT_TIMESTAMP, 'A') RETURNING cht_nr_id INTO v_cht_bd;

    INSERT INTO academico.mtr_matricula_turma (alu_nr_id, tur_nr_id, mtr_tx_status)
    VALUES (v_alu_joao, v_tur1_id, 'A') RETURNING mtr_nr_id INTO v_mtr3;

    INSERT INTO academico.mtr_matricula_turma (alu_nr_id, tur_nr_id, mtr_tx_status)
    VALUES (v_alu_joao, v_tur2_id, 'A') RETURNING mtr_nr_id INTO v_mtr4;

    INSERT INTO academico.mtr_matricula_turma (alu_nr_id, tur_nr_id, mtr_tx_status)
    VALUES (v_alu_ana, v_tur1_id, 'A') RETURNING mtr_nr_id INTO v_mtr5;

    INSERT INTO academico.mtr_matricula_turma (alu_nr_id, tur_nr_id, mtr_tx_status)
    VALUES (v_alu_ana, v_tur_bd, 'A') RETURNING mtr_nr_id INTO v_mtr6;

    INSERT INTO academico.mtr_matricula_turma (alu_nr_id, tur_nr_id, mtr_tx_status)
    VALUES (v_alu1_id, v_tur_bd, 'A') RETURNING mtr_nr_id INTO v_mtr7;

    INSERT INTO academico.mtr_matricula_turma (alu_nr_id, tur_nr_id, mtr_tx_status)
    VALUES (v_alu1_id, v_tur_hist, 'I') RETURNING mtr_nr_id INTO v_mtr8;

    INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (v_mtr1, 'Prova 1', 7.50, 'A');
    INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (v_mtr1, 'Prova 2', 8.00, 'A');
    INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (v_mtr1, 'Trabalho Final', 9.00, 'A');
    INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (v_mtr2, 'Prova 1', 6.00, 'A');
    INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (v_mtr2, 'Prova 2', 7.50, 'A');
    INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (v_mtr2, 'Projeto Web', 8.50, 'A');
    INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (v_mtr3, 'Prova 1', 8.50, 'A');
    INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (v_mtr3, 'Prova 2', 9.00, 'A');
    INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (v_mtr3, 'Trabalho Final', 8.00, 'A');
    INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (v_mtr4, 'Prova 1', 5.50, 'A');
    INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (v_mtr4, 'Prova 2', 6.50, 'A');
    INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (v_mtr4, 'Projeto Web', 7.00, 'A');
    INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (v_mtr5, 'Prova 1', 9.00, 'A');
    INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (v_mtr5, 'Prova 2', 9.50, 'A');
    INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (v_mtr5, 'Trabalho Final', 10.00, 'A');
    INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (v_mtr6, 'Prova 1', 7.00, 'A');
    INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (v_mtr6, 'Prova 2', 8.00, 'A');
    INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (v_mtr7, 'Prova 1', 6.50, 'A');
    INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (v_mtr7, 'Prova 2', 7.00, 'A');
    INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (v_mtr8, 'Prova 1', 7.00, 'A');
    INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (v_mtr8, 'Prova 2', 8.50, 'A');
    INSERT INTO academico.not_nota (mtr_nr_id, not_tx_descricao, not_nr_valor, not_tx_status) VALUES (v_mtr8, 'Trabalho Final', 9.00, 'A');

    INSERT INTO academico.fal_falta (mtr_nr_id, fal_dt_data, fal_nr_quantidade, fal_tx_observacao, fal_tx_status) VALUES (v_mtr1, '2026-03-10', 2, 'Ausencia justificada', 'A');
    INSERT INTO academico.fal_falta (mtr_nr_id, fal_dt_data, fal_nr_quantidade, fal_tx_observacao, fal_tx_status) VALUES (v_mtr1, '2026-04-05', 1, NULL, 'A');
    INSERT INTO academico.fal_falta (mtr_nr_id, fal_dt_data, fal_nr_quantidade, fal_tx_observacao, fal_tx_status) VALUES (v_mtr2, '2026-03-15', 1, NULL, 'A');
    INSERT INTO academico.fal_falta (mtr_nr_id, fal_dt_data, fal_nr_quantidade, fal_tx_observacao, fal_tx_status) VALUES (v_mtr3, '2026-02-20', 2, 'Atestado medico', 'A');
    INSERT INTO academico.fal_falta (mtr_nr_id, fal_dt_data, fal_nr_quantidade, fal_tx_observacao, fal_tx_status) VALUES (v_mtr3, '2026-03-12', 1, NULL, 'A');
    INSERT INTO academico.fal_falta (mtr_nr_id, fal_dt_data, fal_nr_quantidade, fal_tx_observacao, fal_tx_status) VALUES (v_mtr4, '2026-03-20', 1, 'Problema pessoal', 'A');
    INSERT INTO academico.fal_falta (mtr_nr_id, fal_dt_data, fal_nr_quantidade, fal_tx_observacao, fal_tx_status) VALUES (v_mtr6, '2026-03-05', 1, NULL, 'A');
    INSERT INTO academico.fal_falta (mtr_nr_id, fal_dt_data, fal_nr_quantidade, fal_tx_observacao, fal_tx_status) VALUES (v_mtr6, '2026-04-15', 2, 'Consulta medica', 'A');
    INSERT INTO academico.fal_falta (mtr_nr_id, fal_dt_data, fal_nr_quantidade, fal_tx_observacao, fal_tx_status) VALUES (v_mtr7, '2026-03-25', 1, NULL, 'A');
    INSERT INTO academico.fal_falta (mtr_nr_id, fal_dt_data, fal_nr_quantidade, fal_tx_observacao, fal_tx_status) VALUES (v_mtr8, '2025-09-15', 2, NULL, 'A');

    INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status)
    VALUES (v_cht1, v_usu_carlos, 'Bem-vindos a disciplina de Estrutura de Dados!', '2026-02-10 08:00:00', 'A');
    INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status)
    VALUES (v_cht1, v_usu_aluno1, 'Obrigado, professor! Quando sera a primeira prova?', '2026-02-10 09:15:00', 'A');
    INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status)
    VALUES (v_cht1, v_usu_carlos, 'A primeira prova sera na semana 8.', '2026-02-10 10:30:00', 'A');
    INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status)
    VALUES (v_cht1, v_usu_joao, 'Professor, o trabalho pode ser feito em dupla?', '2026-02-12 14:00:00', 'A');
    INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status)
    VALUES (v_cht1, v_usu_carlos, 'Sim, pode ser em dupla ou individual.', '2026-02-12 15:00:00', 'A');
    INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status)
    VALUES (v_cht1, v_usu_aluno1, 'Mensagem removida pelo autor.', '2026-02-13 08:00:00', 'I');

    INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status)
    VALUES (v_cht2, v_usu_carlos, 'Ola turma! Nosso projeto web sera desenvolvido ao longo do semestre.', '2026-02-11 08:00:00', 'A');
    INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status)
    VALUES (v_cht2, v_usu_aluno1, 'Professor, podemos usar React?', '2026-02-11 10:00:00', 'A');
    INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status)
    VALUES (v_cht2, v_usu_carlos, 'Sim, React ou Angular sao permitidos.', '2026-02-11 11:30:00', 'A');
    INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status)
    VALUES (v_cht2, v_usu_joao, 'Teste de mensagem inativa.', '2026-02-14 09:00:00', 'I');

    INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status)
    VALUES (v_cht_bd, v_usu_maria, 'Bem-vindos ao chat de Banco de Dados!', '2026-02-12 08:00:00', 'A');
    INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status)
    VALUES (v_cht_bd, v_usu_ana, 'Bom dia, professora! Estou animada para a disciplina.', '2026-02-12 09:00:00', 'A');
    INSERT INTO academico.msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem, msg_dt_envio, msg_tx_status)
    VALUES (v_cht_bd, v_usu_maria, 'Vamos trabalhar bastante com SQL neste semestre.', '2026-02-12 10:00:00', 'A');

END $$;
