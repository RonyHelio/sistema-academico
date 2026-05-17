package br.com.rony.academico.sistema_academico.controller;

import br.com.rony.academico.sistema_academico.dto.request.AlunoRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.AlunoResponseDTO;
import br.com.rony.academico.sistema_academico.dto.response.FaltaResponseDTO;
import br.com.rony.academico.sistema_academico.dto.response.NotaResponseDTO;
import br.com.rony.academico.sistema_academico.dto.response.SituacaoAlunoTurmaDTO;
import br.com.rony.academico.sistema_academico.dto.response.TurmaResponseDTO;
import br.com.rony.academico.sistema_academico.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> salvar(@Valid @RequestBody AlunoRequestDTO dto, @RequestHeader("usuario-id") Long usuarioId) {
        return ResponseEntity.ok(alunoService.salvar(dto, usuarioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(alunoService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody AlunoRequestDTO dto, @RequestHeader("usuario-id") Long usuarioId) {
        return ResponseEntity.ok(alunoService.atualizar(id, dto, usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Long id, @RequestHeader("usuario-id") Long usuarioId) {
        alunoService.inativar(id, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{alunoId}/turmas")
    public ResponseEntity<List<TurmaResponseDTO>> listarTurmas(@PathVariable Long alunoId, @RequestHeader("usuario-id") Long usuarioId) {
        return ResponseEntity.ok(alunoService.listarTurmasDoAluno(alunoId, usuarioId));
    }

    @GetMapping("/{alunoId}/notas")
    public ResponseEntity<List<NotaResponseDTO>> listarNotas(@PathVariable Long alunoId, @RequestHeader("usuario-id") Long usuarioId) {
        return ResponseEntity.ok(alunoService.listarNotasDoAluno(alunoId, usuarioId));
    }

    @GetMapping("/{alunoId}/faltas")
    public ResponseEntity<List<FaltaResponseDTO>> listarFaltas(@PathVariable Long alunoId, @RequestHeader("usuario-id") Long usuarioId) {
        return ResponseEntity.ok(alunoService.listarFaltasDoAluno(alunoId, usuarioId));
    }

    @GetMapping("/{alunoId}/situacao")
    public ResponseEntity<List<SituacaoAlunoTurmaDTO>> consultarSituacao(@PathVariable Long alunoId, @RequestHeader("usuario-id") Long usuarioId) {
        return ResponseEntity.ok(alunoService.consultarSituacao(alunoId, usuarioId));
    }
}
