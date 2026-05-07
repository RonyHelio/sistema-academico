package br.com.rony.academico.sistema_academico.controller;

import br.com.rony.academico.sistema_academico.dto.ApiResponse;
import br.com.rony.academico.sistema_academico.dto.request.MensagemChatRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.MensagemChatResponseDTO;
import br.com.rony.academico.sistema_academico.service.MensagemChatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável pelo gerenciamento de mensagens do chat por turma.
 * Apenas recebe requisições, valida e delega ao service.
 */
@RestController
@RequestMapping("/api/mensagens")
public class MensagemChatController {

    @Autowired
    private MensagemChatService mensagemChatService;

    @PostMapping
    public ResponseEntity<ApiResponse<MensagemChatResponseDTO>> enviarMensagem(@Valid @RequestBody MensagemChatRequestDTO dto) {
        MensagemChatResponseDTO resposta = mensagemChatService.enviarMensagem(dto);
        return ResponseEntity.ok(ApiResponse.sucesso(resposta));
    }

    @GetMapping("/chat/{chatTurmaId}")
    public ResponseEntity<ApiResponse<List<MensagemChatResponseDTO>>> listarMensagens(@PathVariable Long chatTurmaId) {
        List<MensagemChatResponseDTO> resposta = mensagemChatService.listarMensagensDoChat(chatTurmaId);
        return ResponseEntity.ok(ApiResponse.sucesso(resposta));
    }
}
