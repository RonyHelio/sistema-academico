package br.com.rony.academico.sistema_academico.controller;

import br.com.rony.academico.sistema_academico.model.MensagemChat;
import br.com.rony.academico.sistema_academico.service.MensagemChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")

public class MensagemChatController {

    @Autowired
    private MensagemChatService chatService;

    @PostMapping("/enviar")
    public ResponseEntity<MensagemChat> enviarMensagem(@RequestBody MensagemChat novaMensagem) {
        MensagemChat mensagemSalva = chatService.enviarMensagem(novaMensagem);
        return ResponseEntity.ok(mensagemSalva);
    }

    @GetMapping("/turma/{chatTurmaId}")
    public ResponseEntity<List<MensagemChat>> listarMensagens(@PathVariable Long chatTurmaId) {
        List<MensagemChat> historico = chatService.listarMensagensDoChat(chatTurmaId);
        return ResponseEntity.ok(historico);
    }
}
