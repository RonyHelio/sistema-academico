package br.com.rony.academico.sistema_academico.controller;

import br.com.rony.academico.sistema_academico.dto.ApiResponse;
import br.com.rony.academico.sistema_academico.dto.request.CursoRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.CursoResponseDTO;
import br.com.rony.academico.sistema_academico.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável pelo gerenciamento de cursos.
 */
@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<ApiResponse<CursoResponseDTO>> salvar(@Valid @RequestBody CursoRequestDTO dto) {
        return ResponseEntity.ok(ApiResponse.sucesso(cursoService.salvar(dto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CursoResponseDTO>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.sucesso(cursoService.buscarPorId(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CursoResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.sucesso(cursoService.listarTodos()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CursoResponseDTO>> atualizar(@PathVariable Long id, @Valid @RequestBody CursoRequestDTO dto) {
        return ResponseEntity.ok(ApiResponse.sucesso(cursoService.atualizar(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> inativar(@PathVariable Long id) {
        cursoService.inativar(id);
        return ResponseEntity.ok(ApiResponse.sucesso(null));
    }
}
