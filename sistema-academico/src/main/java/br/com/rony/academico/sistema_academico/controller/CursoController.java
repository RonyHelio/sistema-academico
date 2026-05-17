package br.com.rony.academico.sistema_academico.controller;

import br.com.rony.academico.sistema_academico.dto.request.CursoRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.CursoResponseDTO;
import br.com.rony.academico.sistema_academico.service.CursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @PostMapping
    public ResponseEntity<CursoResponseDTO> salvar(@Valid @RequestBody CursoRequestDTO dto, @RequestHeader("usuario-id") Long usuarioId) {
        return ResponseEntity.ok(cursoService.salvar(dto, usuarioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<CursoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(cursoService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody CursoRequestDTO dto, @RequestHeader("usuario-id") Long usuarioId) {
        return ResponseEntity.ok(cursoService.atualizar(id, dto, usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Long id, @RequestHeader("usuario-id") Long usuarioId) {
        cursoService.inativar(id, usuarioId);
        return ResponseEntity.noContent().build();
    }
}
