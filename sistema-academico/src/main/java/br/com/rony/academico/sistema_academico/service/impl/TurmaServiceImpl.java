package br.com.rony.academico.sistema_academico.service.impl;

import br.com.rony.academico.sistema_academico.dto.request.TurmaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.TurmaResponseDTO;
import br.com.rony.academico.sistema_academico.entity.Disciplina;
import br.com.rony.academico.sistema_academico.entity.PeriodoLetivo;
import br.com.rony.academico.sistema_academico.entity.Professor;
import br.com.rony.academico.sistema_academico.entity.Turma;
import br.com.rony.academico.sistema_academico.entity.Usuario;
import br.com.rony.academico.sistema_academico.mapper.TurmaMapper;
import br.com.rony.academico.sistema_academico.repository.DisciplinaRepository;
import br.com.rony.academico.sistema_academico.repository.PeriodoLetivoRepository;
import br.com.rony.academico.sistema_academico.repository.ProfessorRepository;
import br.com.rony.academico.sistema_academico.repository.TurmaRepository;
import br.com.rony.academico.sistema_academico.repository.UsuarioRepository;
import br.com.rony.academico.sistema_academico.service.TurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TurmaServiceImpl implements TurmaService {

    private final TurmaRepository turmaRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepository professorRepository;
    private final PeriodoLetivoRepository periodoLetivoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public TurmaResponseDTO salvar(TurmaRequestDTO dto, Long usuarioId) {
        validarCoordenador(usuarioId);

        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada."));
        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado."));
        PeriodoLetivo periodoLetivo = periodoLetivoRepository.findById(dto.getPeriodoLetivoId())
                .orElseThrow(() -> new RuntimeException("Período letivo não encontrado."));

        Turma turma = TurmaMapper.toEntity(dto, disciplina, professor, periodoLetivo);
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
    public TurmaResponseDTO atualizar(Long id, TurmaRequestDTO dto, Long usuarioId) {
        validarCoordenador(usuarioId);

        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada."));
        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada."));
        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado."));
        PeriodoLetivo periodoLetivo = periodoLetivoRepository.findById(dto.getPeriodoLetivoId())
                .orElseThrow(() -> new RuntimeException("Período letivo não encontrado."));

        turma.setDisciplina(disciplina);
        turma.setProfessor(professor);
        turma.setPeriodoLetivo(periodoLetivo);
        turma.setDescricao(dto.getDescricao());
        turma.setCodigoSuap(dto.getCodigoSuap());

        Turma atualizada = turmaRepository.save(turma);
        return TurmaMapper.toDTO(atualizada);
    }

    @Override
    public void inativar(Long id, Long usuarioId) {
        validarCoordenador(usuarioId);
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada."));
        turma.setStatus("I");
        turmaRepository.save(turma);
    }

    private void validarCoordenador(Long usuarioId) {
        Usuario solicitante = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        if (!"COORDENADOR".equals(solicitante.getPerfil())) {
            throw new RuntimeException("Acesso negado. Apenas coordenadores podem realizar esta operação.");
        }
    }
}
