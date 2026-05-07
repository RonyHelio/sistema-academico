package br.com.rony.academico.sistema_academico.controller;

import br.com.rony.academico.sistema_academico.dto.request.FaltaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.FaltaResponseDTO;
import br.com.rony.academico.sistema_academico.service.FaltaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável pelo gerenciamento de faltas.
 */
@RestController
@RequestMapping("/api/faltas")
@RequiredArgsConstructor
public class FaltaController {

    private final FaltaService faltaService;

    @PostMapping
    public ResponseEntity<FaltaResponseDTO> salvar(@Valid @RequestBody FaltaRequestDTO dto) {
        return ResponseEntity.ok(faltaService.salvar(dto));
    }

    @GetMapping("/matricula/{matriculaTurmaId}")
    public ResponseEntity<List<FaltaResponseDTO>> listarPorMatricula(@PathVariable Long matriculaTurmaId) {
        return ResponseEntity.ok(faltaService.listarPorMatriculaTurma(matriculaTurmaId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        faltaService.inativar(id);
        return ResponseEntity.noContent().build();
    }
}
