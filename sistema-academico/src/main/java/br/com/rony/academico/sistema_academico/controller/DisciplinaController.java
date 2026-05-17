package br.com.rony.academico.sistema_academico.controller;

import br.com.rony.academico.sistema_academico.dto.request.DisciplinaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.DisciplinaResponseDTO;
import br.com.rony.academico.sistema_academico.service.DisciplinaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
@RequiredArgsConstructor
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    @PostMapping
    public ResponseEntity<DisciplinaResponseDTO> salvar(@Valid @RequestBody DisciplinaRequestDTO dto, @RequestHeader("usuario-id") Long usuarioId) {
        return ResponseEntity.ok(disciplinaService.salvar(dto, usuarioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(disciplinaService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(disciplinaService.listarTodas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody DisciplinaRequestDTO dto, @RequestHeader("usuario-id") Long usuarioId) {
        return ResponseEntity.ok(disciplinaService.atualizar(id, dto, usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Long id, @RequestHeader("usuario-id") Long usuarioId) {
        disciplinaService.inativar(id, usuarioId);
        return ResponseEntity.noContent().build();
    }
}
