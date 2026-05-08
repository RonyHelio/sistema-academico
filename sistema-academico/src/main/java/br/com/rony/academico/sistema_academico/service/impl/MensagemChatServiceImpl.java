package br.com.rony.academico.sistema_academico.service.impl;

import br.com.rony.academico.sistema_academico.dto.request.MensagemChatRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.MensagemChatResponseDTO;
import br.com.rony.academico.sistema_academico.entity.ChatTurma;
import br.com.rony.academico.sistema_academico.entity.MensagemChat;
import br.com.rony.academico.sistema_academico.entity.Usuario;
import br.com.rony.academico.sistema_academico.mapper.MensagemChatMapper;
import br.com.rony.academico.sistema_academico.repository.ChatTurmaRepository;
import br.com.rony.academico.sistema_academico.repository.MensagemChatRepository;
import br.com.rony.academico.sistema_academico.repository.UsuarioRepository;
import br.com.rony.academico.sistema_academico.service.MensagemChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MensagemChatServiceImpl implements MensagemChatService {

    private final MensagemChatRepository mensagemRepository;
    private final ChatTurmaRepository chatTurmaRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public MensagemChatResponseDTO enviarMensagem(MensagemChatRequestDTO dto) {
        ChatTurma chat = chatTurmaRepository.findById(dto.getChatTurmaId())
                .orElseThrow(() -> new RuntimeException("Chat da turma não encontrado."));

        Usuario remetente = usuarioRepository.findById(dto.getRemetenteId())
                .orElseThrow(() -> new RuntimeException("Usuário remetente não encontrado."));

        MensagemChat novaMensagem = MensagemChatMapper.toEntity(dto, chat, remetente);
        MensagemChat salva = mensagemRepository.save(novaMensagem);
        return MensagemChatMapper.toDTO(salva);
    }

    @Override
    public List<MensagemChatResponseDTO> listarMensagensDoChat(Long chatTurmaId) {
        return mensagemRepository.findByChatTurmaIdOrderByDataEnvioAsc(chatTurmaId)
                .stream()
                .map(MensagemChatMapper::toDTO)
                .collect(Collectors.toList());
    }
}
