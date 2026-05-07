package br.com.rony.academico.sistema_academico.service;

import br.com.rony.academico.sistema_academico.dto.request.MensagemChatRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.MensagemChatResponseDTO;

import java.util.List;

/**
 * Interface de serviço para gerenciamento de mensagens do chat por turma.
 */
public interface MensagemChatService {

    MensagemChatResponseDTO enviarMensagem(MensagemChatRequestDTO dto);

    List<MensagemChatResponseDTO> listarMensagensDoChat(Long chatTurmaId);
}
