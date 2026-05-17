package br.com.rony.academico.sistema_academico.controller;

import br.com.rony.academico.sistema_academico.dto.request.TurmaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.TurmaResponseDTO;
import br.com.rony.academico.sistema_academico.service.TurmaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas")
@RequiredArgsConstructor
public class TurmaController {

    private final TurmaService turmaService;

    @PostMapping
    public ResponseEntity<TurmaResponseDTO> salvar(@Valid @RequestBody TurmaRequestDTO dto, @RequestHeader("usuario-id") Long usuarioId) {
        return ResponseEntity.ok(turmaService.salvar(dto, usuarioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(turmaService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<TurmaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(turmaService.listarTodas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody TurmaRequestDTO dto, @RequestHeader("usuario-id") Long usuarioId) {
        return ResponseEntity.ok(turmaService.atualizar(id, dto, usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Long id, @RequestHeader("usuario-id") Long usuarioId) {
        turmaService.inativar(id, usuarioId);
        return ResponseEntity.noContent().build();
    }
}
