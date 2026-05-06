package br.com.rony.academico.sistema_academico.service;


import br.com.rony.academico.sistema_academico.model.MensagemChat;
import br.com.rony.academico.sistema_academico.repository.MensagemChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class MensagemChatService {

    @Autowired
    private MensagemChatRepository mensagemRepository;

    public MensagemChat enviarMensagem(MensagemChat novaMensagem) {

        novaMensagem.setDataEnvio(LocalDateTime.now());
        novaMensagem.setStatus("O");

        return mensagemRepository.save(novaMensagem);
    }

    public List<MensagemChat> listarMensagensDoChat(Long chatTurmaId){
        return mensagemRepository.findByChatTurmaIdOrderByDataEnvioAsc(chatTurmaId);
    }
}
