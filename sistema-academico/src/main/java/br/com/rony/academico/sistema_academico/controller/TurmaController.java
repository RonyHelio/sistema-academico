package br.com.rony.academico.sistema_academico.controller;

import br.com.rony.academico.sistema_academico.dto.ApiResponse;
import br.com.rony.academico.sistema_academico.dto.request.TurmaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.TurmaResponseDTO;
import br.com.rony.academico.sistema_academico.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável pelo gerenciamento de turmas.
 */
@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @PostMapping
    public ResponseEntity<ApiResponse<TurmaResponseDTO>> salvar(@Valid @RequestBody TurmaRequestDTO dto) {
        return ResponseEntity.ok(ApiResponse.sucesso(turmaService.salvar(dto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TurmaResponseDTO>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.sucesso(turmaService.buscarPorId(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TurmaResponseDTO>>> listarTodas() {
        return ResponseEntity.ok(ApiResponse.sucesso(turmaService.listarTodas()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TurmaResponseDTO>> atualizar(@PathVariable Long id, @Valid @RequestBody TurmaRequestDTO dto) {
        return ResponseEntity.ok(ApiResponse.sucesso(turmaService.atualizar(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> inativar(@PathVariable Long id) {
        turmaService.inativar(id);
        return ResponseEntity.ok(ApiResponse.sucesso(null));
    }
}
