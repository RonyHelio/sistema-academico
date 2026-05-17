package br.com.rony.academico.sistema_academico.controller;

import br.com.rony.academico.sistema_academico.dto.response.MatriculaTurmaResponseDTO;
import br.com.rony.academico.sistema_academico.dto.response.TurmaResponseDTO;
import br.com.rony.academico.sistema_academico.service.MatriculaTurmaService;
import br.com.rony.academico.sistema_academico.repository.TurmaRepository;
import br.com.rony.academico.sistema_academico.mapper.TurmaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/professores")
@RequiredArgsConstructor
public class ProfessorController {

    private final TurmaRepository turmaRepository;
    private final MatriculaTurmaService matriculaTurmaService;

    @GetMapping("/{professorId}/turmas")
    public ResponseEntity<List<TurmaResponseDTO>> listarTurmasDoProfessor(@PathVariable Long professorId) {
        List<TurmaResponseDTO> turmas = turmaRepository.findByProfessorId(professorId)
                .stream()
                .map(TurmaMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(turmas);
    }

    @GetMapping("/{professorId}/turmas/{turmaId}/alunos")
    public ResponseEntity<List<MatriculaTurmaResponseDTO>> listarAlunosDaTurma(
            @PathVariable Long professorId,
            @PathVariable Long turmaId) {
        return ResponseEntity.ok(matriculaTurmaService.listarPorTurma(turmaId));
    }
}
