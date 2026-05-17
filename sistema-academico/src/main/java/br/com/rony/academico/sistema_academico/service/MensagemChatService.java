package br.com.rony.academico.sistema_academico.service;

import br.com.rony.academico.sistema_academico.dto.request.MensagemChatRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.MensagemChatResponseDTO;

import java.util.List;

public interface MensagemChatService {

    MensagemChatResponseDTO enviarMensagem(MensagemChatRequestDTO dto, Long usuarioId);

    List<MensagemChatResponseDTO> listarMensagensDoChat(Long chatTurmaId, Long usuarioId);

    void inativar(Long id, Long usuarioId);
}
