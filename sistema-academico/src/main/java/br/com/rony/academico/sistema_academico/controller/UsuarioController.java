package br.com.rony.academico.sistema_academico.controller;

import br.com.rony.academico.sistema_academico.dto.ApiResponse;
import br.com.rony.academico.sistema_academico.dto.request.UsuarioRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.UsuarioResponseDTO;
import br.com.rony.academico.sistema_academico.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável pelo gerenciamento de usuários.
 * Apenas recebe requisições, valida e delega ao service.
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> salvar(@Valid @RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO resposta = usuarioService.salvar(dto);
        return ResponseEntity.ok(ApiResponse.sucesso(resposta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> buscarPorId(@PathVariable Long id) {
        UsuarioResponseDTO resposta = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(ApiResponse.sucesso(resposta));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UsuarioResponseDTO>>> listarTodos() {
        List<UsuarioResponseDTO> resposta = usuarioService.listarTodos();
        return ResponseEntity.ok(ApiResponse.sucesso(resposta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO resposta = usuarioService.atualizar(id, dto);
        return ResponseEntity.ok(ApiResponse.sucesso(resposta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> inativar(@PathVariable Long id) {
        usuarioService.inativar(id);
        return ResponseEntity.ok(ApiResponse.sucesso(null));
    }
}
