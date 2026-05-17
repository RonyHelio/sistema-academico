package br.com.rony.academico.sistema_academico.controller;

import br.com.rony.academico.sistema_academico.dto.request.UsuarioRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.UsuarioResponseDTO;
import br.com.rony.academico.sistema_academico.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> salvar(@Valid @RequestBody UsuarioRequestDTO dto, @RequestHeader("usuario-id") Long usuarioId) {
        return ResponseEntity.ok(usuarioService.salvar(dto, usuarioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO dto, @RequestHeader("usuario-id") Long usuarioId) {
        return ResponseEntity.ok(usuarioService.atualizar(id, dto, usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Long id, @RequestHeader("usuario-id") Long usuarioId) {
        usuarioService.inativar(id, usuarioId);
        return ResponseEntity.noContent().build();
    }
}
