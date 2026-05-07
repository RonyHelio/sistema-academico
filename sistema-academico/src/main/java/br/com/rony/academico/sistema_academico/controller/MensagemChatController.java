package br.com.rony.academico.sistema_academico.controller;

import br.com.rony.academico.sistema_academico.dto.MensagemChatRequestDTO;
import br.com.rony.academico.sistema_academico.dto.MensagemChatResponseDTO;
import br.com.rony.academico.sistema_academico.model.MensagemChat;
import br.com.rony.academico.sistema_academico.service.MensagemChatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")

public class MensagemChatController {

    @Autowired
    private MensagemChatService chatService;

    @PostMapping("/enviar")
    public ResponseEntity<MensagemChatResponseDTO> enviarMensagem(@Valid @RequestBody MensagemChatRequestDTO requestDTO) {
        MensagemChatResponseDTO mensagemSalva = chatService.enviarMensagem(requestDTO);
        return ResponseEntity.ok(mensagemSalva);
    }

    @GetMapping("/turma/{chatTurmaId}")
    public ResponseEntity<List<MensagemChatResponseDTO>> listarMensagens(@PathVariable Long chatTurmaId) {
        List<MensagemChatResponseDTO> historico = chatService.listarMensagensDoChat(chatTurmaId);
        return ResponseEntity.ok(historico);
    }
}
