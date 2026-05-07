package br.com.rony.academico.sistema_academico.controller;

import br.com.rony.academico.sistema_academico.dto.request.MensagemChatRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.MensagemChatResponseDTO;
import br.com.rony.academico.sistema_academico.service.MensagemChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável pelo gerenciamento de mensagens do chat por turma.
 * Apenas recebe requisições, valida e delega ao service.
 */
@RestController
@RequestMapping("/api/mensagens")
@RequiredArgsConstructor
public class MensagemChatController {

    private final MensagemChatService mensagemChatService;

    @PostMapping
    public ResponseEntity<MensagemChatResponseDTO> enviarMensagem(@Valid @RequestBody MensagemChatRequestDTO dto) {
        return ResponseEntity.ok(mensagemChatService.enviarMensagem(dto));
    }

    @GetMapping("/chat/{chatTurmaId}")
    public ResponseEntity<List<MensagemChatResponseDTO>> listarMensagens(@PathVariable Long chatTurmaId) {
        return ResponseEntity.ok(mensagemChatService.listarMensagensDoChat(chatTurmaId));
    }
}
