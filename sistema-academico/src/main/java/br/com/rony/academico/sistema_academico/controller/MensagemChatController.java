package br.com.rony.academico.sistema_academico.controller;

import br.com.rony.academico.sistema_academico.dto.request.MensagemChatRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.MensagemChatResponseDTO;
import br.com.rony.academico.sistema_academico.service.MensagemChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mensagens")
@RequiredArgsConstructor
public class MensagemChatController {

    private final MensagemChatService mensagemChatService;

    @PostMapping
    public ResponseEntity<MensagemChatResponseDTO> enviarMensagem(@Valid @RequestBody MensagemChatRequestDTO dto, @RequestHeader("usuario-id") Long usuarioId) {
        return ResponseEntity.ok(mensagemChatService.enviarMensagem(dto, usuarioId));
    }

    @GetMapping("/chat/{chatTurmaId}")
    public ResponseEntity<List<MensagemChatResponseDTO>> listarMensagens(@PathVariable Long chatTurmaId, @RequestHeader("usuario-id") Long usuarioId) {
        return ResponseEntity.ok(mensagemChatService.listarMensagensDoChat(chatTurmaId, usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Long id, @RequestHeader("usuario-id") Long usuarioId) {
        mensagemChatService.inativar(id, usuarioId);
        return ResponseEntity.noContent().build();
    }
}
