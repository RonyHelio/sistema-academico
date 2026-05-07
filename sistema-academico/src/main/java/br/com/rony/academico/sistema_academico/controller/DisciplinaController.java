package br.com.rony.academico.sistema_academico.controller;

import br.com.rony.academico.sistema_academico.dto.ApiResponse;
import br.com.rony.academico.sistema_academico.dto.request.DisciplinaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.DisciplinaResponseDTO;
import br.com.rony.academico.sistema_academico.service.DisciplinaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável pelo gerenciamento de disciplinas.
 * Apenas recebe requisições, valida e delega ao service.
 */
@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @PostMapping
    public ResponseEntity<ApiResponse<DisciplinaResponseDTO>> salvar(@Valid @RequestBody DisciplinaRequestDTO dto) {
        DisciplinaResponseDTO resposta = disciplinaService.salvar(dto);
        return ResponseEntity.ok(ApiResponse.sucesso(resposta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DisciplinaResponseDTO>> buscarPorId(@PathVariable Long id) {
        DisciplinaResponseDTO resposta = disciplinaService.buscarPorId(id);
        return ResponseEntity.ok(ApiResponse.sucesso(resposta));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DisciplinaResponseDTO>>> listarTodas() {
        List<DisciplinaResponseDTO> resposta = disciplinaService.listarTodas();
        return ResponseEntity.ok(ApiResponse.sucesso(resposta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DisciplinaResponseDTO>> atualizar(@PathVariable Long id, @Valid @RequestBody DisciplinaRequestDTO dto) {
        DisciplinaResponseDTO resposta = disciplinaService.atualizar(id, dto);
        return ResponseEntity.ok(ApiResponse.sucesso(resposta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> inativar(@PathVariable Long id) {
        disciplinaService.inativar(id);
        return ResponseEntity.ok(ApiResponse.sucesso(null));
    }
}
