package br.com.rony.academico.sistema_academico.controller;

import br.com.rony.academico.sistema_academico.dto.request.MatriculaTurmaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.MatriculaTurmaResponseDTO;
import br.com.rony.academico.sistema_academico.service.MatriculaTurmaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
@RequiredArgsConstructor
public class MatriculaTurmaController {

    private final MatriculaTurmaService matriculaTurmaService;

    @PostMapping
    public ResponseEntity<MatriculaTurmaResponseDTO> salvar(@Valid @RequestBody MatriculaTurmaRequestDTO dto, @RequestHeader("usuario-id") Long usuarioId) {
        return ResponseEntity.ok(matriculaTurmaService.salvar(dto, usuarioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaTurmaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaTurmaService.buscarPorId(id));
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<MatriculaTurmaResponseDTO>> listarPorAluno(@PathVariable Long alunoId) {
        return ResponseEntity.ok(matriculaTurmaService.listarPorAluno(alunoId));
    }

    @GetMapping("/turma/{turmaId}")
    public ResponseEntity<List<MatriculaTurmaResponseDTO>> listarPorTurma(@PathVariable Long turmaId) {
        return ResponseEntity.ok(matriculaTurmaService.listarPorTurma(turmaId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Long id, @RequestHeader("usuario-id") Long usuarioId) {
        matriculaTurmaService.inativar(id, usuarioId);
        return ResponseEntity.noContent().build();
    }
}
