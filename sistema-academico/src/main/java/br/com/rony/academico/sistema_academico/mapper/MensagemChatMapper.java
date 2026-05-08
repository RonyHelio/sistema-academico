package br.com.rony.academico.sistema_academico.mapper;

import br.com.rony.academico.sistema_academico.dto.request.MensagemChatRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.MensagemChatResponseDTO;
import br.com.rony.academico.sistema_academico.entity.ChatTurma;
import br.com.rony.academico.sistema_academico.entity.MensagemChat;
import br.com.rony.academico.sistema_academico.entity.Usuario;

import java.time.LocalDateTime;

public class MensagemChatMapper {

    public static MensagemChat toEntity(MensagemChatRequestDTO dto, ChatTurma chatTurma, Usuario remetente) {
        return MensagemChat.builder()
                .chatTurma(chatTurma)
                .remetente(remetente)
                .texto(dto.getTexto())
                .dataEnvio(LocalDateTime.now())
                .status("A")
                .build();
    }

    public static MensagemChatResponseDTO toDTO(MensagemChat entity) {
        return MensagemChatResponseDTO.builder()
                .id(entity.getId())
                .chatTurmaId(entity.getChatTurma().getId())
                .remetenteId(entity.getRemetente().getId())
                .nomeRemetente(entity.getRemetente().getNome())
                .texto(entity.getTexto())
                .dataEnvio(entity.getDataEnvio())
                .status(entity.getStatus())
                .build();
    }
}
