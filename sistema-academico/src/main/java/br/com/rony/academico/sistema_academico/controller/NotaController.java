package br.com.rony.academico.sistema_academico.controller;

import br.com.rony.academico.sistema_academico.dto.ApiResponse;
import br.com.rony.academico.sistema_academico.dto.request.NotaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.NotaResponseDTO;
import br.com.rony.academico.sistema_academico.service.NotaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável pelo gerenciamento de notas.
 */
@RestController
@RequestMapping("/api/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    @PostMapping
    public ResponseEntity<ApiResponse<NotaResponseDTO>> salvar(@Valid @RequestBody NotaRequestDTO dto) {
        return ResponseEntity.ok(ApiResponse.sucesso(notaService.salvar(dto)));
    }

    @GetMapping("/matricula/{matriculaTurmaId}")
    public ResponseEntity<ApiResponse<List<NotaResponseDTO>>> listarPorMatricula(@PathVariable Long matriculaTurmaId) {
        return ResponseEntity.ok(ApiResponse.sucesso(notaService.listarPorMatriculaTurma(matriculaTurmaId)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> inativar(@PathVariable Long id) {
        notaService.inativar(id);
        return ResponseEntity.ok(ApiResponse.sucesso(null));
    }
}
