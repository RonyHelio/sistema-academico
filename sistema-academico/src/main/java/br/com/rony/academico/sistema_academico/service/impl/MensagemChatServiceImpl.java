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
    public MensagemChatResponseDTO enviarMensagem(MensagemChatRequestDTO dto, Long usuarioId) {
        Usuario remetente = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário remetente não encontrado."));

        ChatTurma chat = chatTurmaRepository.findById(dto.getChatTurmaId())
                .orElseThrow(() -> new RuntimeException("Chat da turma não encontrado."));

        MensagemChat novaMensagem = MensagemChatMapper.toEntity(dto, chat, remetente);
        MensagemChat salva = mensagemRepository.save(novaMensagem);
        return MensagemChatMapper.toDTO(salva);
    }

    @Override
    public List<MensagemChatResponseDTO> listarMensagensDoChat(Long chatTurmaId, Long usuarioId) {
        usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        return mensagemRepository.findByChatTurmaIdOrderByDataEnvioAsc(chatTurmaId)
                .stream()
                .map(MensagemChatMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void inativar(Long id, Long usuarioId) {
        Usuario solicitante = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        MensagemChat mensagem = mensagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensagem não encontrada."));

        if (!"COORDENADOR".equals(solicitante.getPerfil()) && !mensagem.getRemetente().getId().equals(usuarioId)) {
            throw new RuntimeException("Acesso negado. Apenas o autor ou coordenadores podem inativar esta mensagem.");
        }

        mensagem.setStatus("I");
        mensagemRepository.save(mensagem);
    }
}
