package br.com.rony.academico.sistema_academico.service;


import br.com.rony.academico.sistema_academico.dto.MensagemChatRequestDTO;
import br.com.rony.academico.sistema_academico.dto.MensagemChatResponseDTO;
import br.com.rony.academico.sistema_academico.model.ChatTurma;
import br.com.rony.academico.sistema_academico.model.MensagemChat;
import br.com.rony.academico.sistema_academico.model.Usuario;
import br.com.rony.academico.sistema_academico.repository.ChatTurmaRepository;
import br.com.rony.academico.sistema_academico.repository.MensagemChatRepository;
import br.com.rony.academico.sistema_academico.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MensagemChatService {

    @Autowired
    private MensagemChatRepository mensagemRepository;

    @Autowired
    private ChatTurmaRepository chatTurmaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public MensagemChatResponseDTO enviarMensagem(MensagemChatRequestDTO requestDTO) {

        ChatTurma chat = chatTurmaRepository.findById(requestDTO.getChatTurmaId()).
                orElseThrow(() -> new RuntimeException("Erro ao enviar Mensagem!"));

        Usuario remetente = usuarioRepository.findById(requestDTO.getRemetenteId()).
                orElseThrow(() -> new RuntimeException("Usuário remetente não encontrado!"));

        MensagemChat novaMensagem = new MensagemChat();
        novaMensagem.setRemetente(remetente);
        novaMensagem.setChatTurma(chat);
        novaMensagem.setTexto(requestDTO.getTexto());
        novaMensagem.setDataEnvio(LocalDateTime.now());
        novaMensagem.setStatus("O");


        MensagemChat mensagemSalva = mensagemRepository.save(novaMensagem);
       return converterParaDTO(mensagemSalva);
    }


    public List<MensagemChatResponseDTO> listarMensagensDoChat(Long chatTurmaId){

        List<MensagemChat> mensagens = mensagemRepository.findByChatTurmaIdOrderByDataEnvioAsc(chatTurmaId);
        return mensagens.stream().map(this::converterParaDTO).collect(Collectors.toList());

    }

    private MensagemChatResponseDTO converterParaDTO(MensagemChat entidade){
        MensagemChatResponseDTO dto = new MensagemChatResponseDTO();

        dto.setId(entidade.getId());
        dto.setTexto(entidade.getTexto());
        dto.setDataEnvio(entidade.getDataEnvio());
        dto.setStatus(entidade.getStatus());
        dto.setChatTurmaId(entidade.getChatTurma().getId());
        dto.setRemetenteID(entidade.getRemetente().getId());

        return dto;
    }

}
