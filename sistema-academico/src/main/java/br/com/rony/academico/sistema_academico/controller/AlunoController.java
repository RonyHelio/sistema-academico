package br.com.rony.academico.sistema_academico.controller;

import br.com.rony.academico.sistema_academico.dto.ApiResponse;
import br.com.rony.academico.sistema_academico.dto.request.AlunoRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.AlunoResponseDTO;
import br.com.rony.academico.sistema_academico.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável pelo gerenciamento de alunos.
 * Apenas recebe requisições, valida e delega ao service.
 */
@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<ApiResponse<AlunoResponseDTO>> salvar(@Valid @RequestBody AlunoRequestDTO dto) {
        AlunoResponseDTO resposta = alunoService.salvar(dto);
        return ResponseEntity.ok(ApiResponse.sucesso(resposta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AlunoResponseDTO>> buscarPorId(@PathVariable Long id) {
        AlunoResponseDTO resposta = alunoService.buscarPorId(id);
        return ResponseEntity.ok(ApiResponse.sucesso(resposta));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AlunoResponseDTO>>> listarTodos() {
        List<AlunoResponseDTO> resposta = alunoService.listarTodos();
        return ResponseEntity.ok(ApiResponse.sucesso(resposta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AlunoResponseDTO>> atualizar(@PathVariable Long id, @Valid @RequestBody AlunoRequestDTO dto) {
        AlunoResponseDTO resposta = alunoService.atualizar(id, dto);
        return ResponseEntity.ok(ApiResponse.sucesso(resposta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> inativar(@PathVariable Long id) {
        alunoService.inativar(id);
        return ResponseEntity.ok(ApiResponse.sucesso(null));
    }
}
