package br.com.rony.academico.sistema_academico.controller;

import br.com.rony.academico.sistema_academico.dto.request.PeriodoLetivoRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.PeriodoLetivoResponseDTO;
import br.com.rony.academico.sistema_academico.service.PeriodoLetivoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/periodos-letivos")
@RequiredArgsConstructor
public class PeriodoLetivoController {

    private final PeriodoLetivoService periodoLetivoService;

    @PostMapping
    public ResponseEntity<PeriodoLetivoResponseDTO> salvar(@Valid @RequestBody PeriodoLetivoRequestDTO dto, @RequestHeader("usuario-id") Long usuarioId) {
        return ResponseEntity.ok(periodoLetivoService.salvar(dto, usuarioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeriodoLetivoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(periodoLetivoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<PeriodoLetivoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(periodoLetivoService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeriodoLetivoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PeriodoLetivoRequestDTO dto, @RequestHeader("usuario-id") Long usuarioId) {
        return ResponseEntity.ok(periodoLetivoService.atualizar(id, dto, usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Long id, @RequestHeader("usuario-id") Long usuarioId) {
        periodoLetivoService.inativar(id, usuarioId);
        return ResponseEntity.noContent().build();
    }
}
