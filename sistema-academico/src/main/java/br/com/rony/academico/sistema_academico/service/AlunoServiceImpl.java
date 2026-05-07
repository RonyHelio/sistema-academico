package br.com.rony.academico.sistema_academico.service;

import br.com.rony.academico.sistema_academico.dto.request.AlunoRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.AlunoResponseDTO;
import br.com.rony.academico.sistema_academico.entity.Aluno;
import br.com.rony.academico.sistema_academico.entity.Curso;
import br.com.rony.academico.sistema_academico.entity.Usuario;
import br.com.rony.academico.sistema_academico.mapper.AlunoMapper;
import br.com.rony.academico.sistema_academico.repository.AlunoRepository;
import br.com.rony.academico.sistema_academico.repository.CursoRepository;
import br.com.rony.academico.sistema_academico.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do serviço de gerenciamento de alunos.
 * Contém regras de negócio relacionadas a alunos.
 */
@Service
@RequiredArgsConstructor
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository alunoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    @Override
    public AlunoResponseDTO salvar(AlunoRequestDTO dto) {
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
    public AlunoResponseDTO atualizar(Long id, AlunoRequestDTO dto) {
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
    public void inativar(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado."));
        aluno.setStatus("I");
        alunoRepository.save(aluno);
    }
}
