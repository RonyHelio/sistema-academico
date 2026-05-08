package br.com.rony.academico.sistema_academico.controller;

import br.com.rony.academico.sistema_academico.dto.request.NotaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.NotaResponseDTO;
import br.com.rony.academico.sistema_academico.service.NotaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
@RequiredArgsConstructor
public class NotaController {

    private final NotaService notaService;

    @PostMapping
    public ResponseEntity<NotaResponseDTO> salvar(@Valid @RequestBody NotaRequestDTO dto) {
        return ResponseEntity.ok(notaService.salvar(dto));
    }

    @GetMapping("/matricula/{matriculaTurmaId}")
    public ResponseEntity<List<NotaResponseDTO>> listarPorMatricula(@PathVariable Long matriculaTurmaId) {
        return ResponseEntity.ok(notaService.listarPorMatriculaTurma(matriculaTurmaId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        notaService.inativar(id);
        return ResponseEntity.noContent().build();
    }
}
