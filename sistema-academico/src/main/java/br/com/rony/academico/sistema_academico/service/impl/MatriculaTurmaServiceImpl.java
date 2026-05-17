package br.com.rony.academico.sistema_academico.service.impl;

import br.com.rony.academico.sistema_academico.dto.request.MatriculaTurmaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.MatriculaTurmaResponseDTO;
import br.com.rony.academico.sistema_academico.entity.Aluno;
import br.com.rony.academico.sistema_academico.entity.MatriculaTurma;
import br.com.rony.academico.sistema_academico.entity.Turma;
import br.com.rony.academico.sistema_academico.entity.Usuario;
import br.com.rony.academico.sistema_academico.mapper.MatriculaTurmaMapper;
import br.com.rony.academico.sistema_academico.repository.AlunoRepository;
import br.com.rony.academico.sistema_academico.repository.MatriculaTurmaRepository;
import br.com.rony.academico.sistema_academico.repository.TurmaRepository;
import br.com.rony.academico.sistema_academico.repository.UsuarioRepository;
import br.com.rony.academico.sistema_academico.service.MatriculaTurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatriculaTurmaServiceImpl implements MatriculaTurmaService {

    private final MatriculaTurmaRepository matriculaTurmaRepository;
    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public MatriculaTurmaResponseDTO salvar(MatriculaTurmaRequestDTO dto, Long usuarioId) {
        validarCoordenador(usuarioId);

        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado."));
        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new RuntimeException("Turma não encontrada."));

        MatriculaTurma matricula = MatriculaTurmaMapper.toEntity(dto, aluno, turma);
        MatriculaTurma salva = matriculaTurmaRepository.save(matricula);
        return MatriculaTurmaMapper.toDTO(salva);
    }

    @Override
    public MatriculaTurmaResponseDTO buscarPorId(Long id) {
        MatriculaTurma matricula = matriculaTurmaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada."));
        return MatriculaTurmaMapper.toDTO(matricula);
    }

    @Override
    public List<MatriculaTurmaResponseDTO> listarPorAluno(Long alunoId) {
        return matriculaTurmaRepository.findByAlunoId(alunoId)
                .stream()
                .map(MatriculaTurmaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MatriculaTurmaResponseDTO> listarPorTurma(Long turmaId) {
        return matriculaTurmaRepository.findByTurmaId(turmaId)
                .stream()
                .map(MatriculaTurmaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void inativar(Long id, Long usuarioId) {
        validarCoordenador(usuarioId);
        MatriculaTurma matricula = matriculaTurmaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada."));
        matricula.setStatus("I");
        matriculaTurmaRepository.save(matricula);
    }

    private void validarCoordenador(Long usuarioId) {
        Usuario solicitante = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        if (!"COORDENADOR".equals(solicitante.getPerfil())) {
            throw new RuntimeException("Acesso negado. Apenas coordenadores podem realizar esta operação.");
        }
    }
}
