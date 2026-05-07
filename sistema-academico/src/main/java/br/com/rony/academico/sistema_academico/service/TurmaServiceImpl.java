package br.com.rony.academico.sistema_academico.service;

import br.com.rony.academico.sistema_academico.dto.request.TurmaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.TurmaResponseDTO;
import br.com.rony.academico.sistema_academico.entity.Disciplina;
import br.com.rony.academico.sistema_academico.entity.Professor;
import br.com.rony.academico.sistema_academico.entity.Turma;
import br.com.rony.academico.sistema_academico.mapper.TurmaMapper;
import br.com.rony.academico.sistema_academico.repository.DisciplinaRepository;
import br.com.rony.academico.sistema_academico.repository.ProfessorRepository;
import br.com.rony.academico.sistema_academico.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do serviço de gerenciamento de turmas.
 * Contém regras de negócio relacionadas a turmas.
 */
@Service
@RequiredArgsConstructor
public class TurmaServiceImpl implements TurmaService {

    private final TurmaRepository turmaRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepository professorRepository;

    @Override
    public TurmaResponseDTO salvar(TurmaRequestDTO dto) {
        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada."));

        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado."));

        Turma turma = TurmaMapper.toEntity(dto, disciplina, professor);
        Turma salva = turmaRepository.save(turma);
        return TurmaMapper.toDTO(salva);
    }

    @Override
    public TurmaResponseDTO buscarPorId(Long id) {
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada."));
        return TurmaMapper.toDTO(turma);
    }

    @Override
    public List<TurmaResponseDTO> listarTodas() {
        return turmaRepository.findAll()
                .stream()
                .map(TurmaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TurmaResponseDTO atualizar(Long id, TurmaRequestDTO dto) {
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada."));

        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada."));

        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado."));

        turma.setDisciplina(disciplina);
        turma.setProfessor(professor);
        turma.setPeriodoLetivoId(dto.getPeriodoLetivoId());
        turma.setDescricao(dto.getDescricao());
        turma.setCodigoSuap(dto.getCodigoSuap());

        Turma atualizada = turmaRepository.save(turma);
        return TurmaMapper.toDTO(atualizada);
    }

    @Override
    public void inativar(Long id) {
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada."));
        turma.setStatus("I");
        turmaRepository.save(turma);
    }
}
