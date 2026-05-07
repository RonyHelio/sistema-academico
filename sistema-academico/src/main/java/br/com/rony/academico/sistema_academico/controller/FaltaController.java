package br.com.rony.academico.sistema_academico.controller;

import br.com.rony.academico.sistema_academico.dto.ApiResponse;
import br.com.rony.academico.sistema_academico.dto.request.FaltaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.FaltaResponseDTO;
import br.com.rony.academico.sistema_academico.service.FaltaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável pelo gerenciamento de faltas.
 */
@RestController
@RequestMapping("/api/faltas")
public class FaltaController {

    @Autowired
    private FaltaService faltaService;

    @PostMapping
    public ResponseEntity<ApiResponse<FaltaResponseDTO>> salvar(@Valid @RequestBody FaltaRequestDTO dto) {
        return ResponseEntity.ok(ApiResponse.sucesso(faltaService.salvar(dto)));
    }

    @GetMapping("/matricula/{matriculaTurmaId}")
    public ResponseEntity<ApiResponse<List<FaltaResponseDTO>>> listarPorMatricula(@PathVariable Long matriculaTurmaId) {
        return ResponseEntity.ok(ApiResponse.sucesso(faltaService.listarPorMatriculaTurma(matriculaTurmaId)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> inativar(@PathVariable Long id) {
        faltaService.inativar(id);
        return ResponseEntity.ok(ApiResponse.sucesso(null));
    }
}
