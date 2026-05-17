package br.com.rony.academico.sistema_academico.service.impl;

import br.com.rony.academico.sistema_academico.dto.request.AlunoRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.AlunoResponseDTO;
import br.com.rony.academico.sistema_academico.dto.response.FaltaResponseDTO;
import br.com.rony.academico.sistema_academico.dto.response.NotaResponseDTO;
import br.com.rony.academico.sistema_academico.dto.response.SituacaoAlunoTurmaDTO;
import br.com.rony.academico.sistema_academico.dto.response.TurmaResponseDTO;
import br.com.rony.academico.sistema_academico.entity.Aluno;
import br.com.rony.academico.sistema_academico.entity.Curso;
import br.com.rony.academico.sistema_academico.entity.Falta;
import br.com.rony.academico.sistema_academico.entity.MatriculaTurma;
import br.com.rony.academico.sistema_academico.entity.Nota;
import br.com.rony.academico.sistema_academico.entity.Usuario;
import br.com.rony.academico.sistema_academico.mapper.AlunoMapper;
import br.com.rony.academico.sistema_academico.mapper.FaltaMapper;
import br.com.rony.academico.sistema_academico.mapper.NotaMapper;
import br.com.rony.academico.sistema_academico.mapper.TurmaMapper;
import br.com.rony.academico.sistema_academico.repository.AlunoRepository;
import br.com.rony.academico.sistema_academico.repository.CursoRepository;
import br.com.rony.academico.sistema_academico.repository.FaltaRepository;
import br.com.rony.academico.sistema_academico.repository.MatriculaTurmaRepository;
import br.com.rony.academico.sistema_academico.repository.NotaRepository;
import br.com.rony.academico.sistema_academico.repository.UsuarioRepository;
import br.com.rony.academico.sistema_academico.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository alunoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;
    private final MatriculaTurmaRepository matriculaTurmaRepository;
    private final NotaRepository notaRepository;
    private final FaltaRepository faltaRepository;

    @Override
    public AlunoResponseDTO salvar(AlunoRequestDTO dto, Long usuarioId) {
        Usuario solicitante = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        if (!"COORDENADOR".equals(solicitante.getPerfil())) {
            throw new RuntimeException("Acesso negado. Apenas coordenadores podem cadastrar alunos.");
        }

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado."));

        Aluno aluno = AlunoMapper.toEntity(dto, usuario, curso);
        Aluno salvo = alunoRepository.save(aluno);
        return AlunoMapper.toDTO(salvo);
    }

    @Override
    public AlunoResponseDTO buscarPorId(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado."));
        return AlunoMapper.toDTO(aluno);
    }

    @Override
    public List<AlunoResponseDTO> listarTodos() {
        return alunoRepository.findAll()
                .stream()
                .map(AlunoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AlunoResponseDTO atualizar(Long id, AlunoRequestDTO dto, Long usuarioId) {
        Usuario solicitante = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        if (!"COORDENADOR".equals(solicitante.getPerfil())) {
            throw new RuntimeException("Acesso negado. Apenas coordenadores podem atualizar alunos.");
        }

        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado."));
        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado."));

        aluno.setCurso(curso);
        aluno.setMatricula(dto.getMatricula());

        Aluno atualizado = alunoRepository.save(aluno);
        return AlunoMapper.toDTO(atualizado);
    }

    @Override
    public void inativar(Long id, Long usuarioId) {
        Usuario solicitante = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        if (!"COORDENADOR".equals(solicitante.getPerfil())) {
            throw new RuntimeException("Acesso negado. Apenas coordenadores podem inativar alunos.");
        }

        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado."));
        aluno.setStatus("I");
        alunoRepository.save(aluno);
    }

    @Override
    public List<TurmaResponseDTO> listarTurmasDoAluno(Long alunoId, Long usuarioId) {
        validarAcessoConsultaAluno(alunoId, usuarioId);

        return matriculaTurmaRepository.findByAlunoId(alunoId)
                .stream()
                .map(matricula -> TurmaMapper.toDTO(matricula.getTurma()))
                .collect(Collectors.toList());
    }

    @Override
    public List<NotaResponseDTO> listarNotasDoAluno(Long alunoId, Long usuarioId) {
        validarAcessoConsultaAluno(alunoId, usuarioId);

        return notaRepository.findByMatriculaTurmaAlunoId(alunoId)
                .stream()
                .map(NotaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FaltaResponseDTO> listarFaltasDoAluno(Long alunoId, Long usuarioId) {
        validarAcessoConsultaAluno(alunoId, usuarioId);

        return faltaRepository.findByMatriculaTurmaAlunoId(alunoId)
                .stream()
                .map(FaltaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SituacaoAlunoTurmaDTO> consultarSituacao(Long alunoId, Long usuarioId) {
        validarAcessoConsultaAluno(alunoId, usuarioId);

        List<MatriculaTurma> matriculas = matriculaTurmaRepository.findByAlunoId(alunoId);
        List<SituacaoAlunoTurmaDTO> situacoes = new ArrayList<>();

        for (MatriculaTurma matricula : matriculas) {
            List<Nota> notas = notaRepository.findByMatriculaTurmaId(matricula.getId());
            List<Falta> faltas = faltaRepository.findByMatriculaTurmaId(matricula.getId());

            BigDecimal media = BigDecimal.ZERO;
            if (!notas.isEmpty()) {
                BigDecimal soma = notas.stream()
                        .map(Nota::getValor)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                media = soma.divide(BigDecimal.valueOf(notas.size()), 2, RoundingMode.HALF_UP);
            }

            int totalFaltas = faltas.stream()
                    .mapToInt(Falta::getQuantidade)
                    .sum();

            String situacao;
            if ("I".equals(matricula.getStatus())) {
                situacao = media.compareTo(BigDecimal.valueOf(7)) >= 0 ? "APROVADO" : "REPROVADO";
            } else {
                situacao = "CURSANDO";
            }

            situacoes.add(SituacaoAlunoTurmaDTO.builder()
                    .turmaId(matricula.getTurma().getId())
                    .nomeDisciplina(matricula.getTurma().getDisciplina().getNome())
                    .descricaoTurma(matricula.getTurma().getDescricao())
                    .periodoLetivo(matricula.getTurma().getPeriodoLetivo().getDescricao())
                    .mediaNotas(media)
                    .totalFaltas(totalFaltas)
                    .situacao(situacao)
                    .build());
        }

        return situacoes;
    }

    private void validarAcessoConsultaAluno(Long alunoId, Long usuarioId) {
        Usuario solicitante = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        if ("ALUNO".equals(solicitante.getPerfil())) {
            Aluno aluno = alunoRepository.findByUsuarioId(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Aluno não encontrado para este usuário."));
            if (!aluno.getId().equals(alunoId)) {
                throw new RuntimeException("Acesso negado. Aluno só pode consultar seus próprios dados.");
            }
        }

        alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado."));
    }
}
