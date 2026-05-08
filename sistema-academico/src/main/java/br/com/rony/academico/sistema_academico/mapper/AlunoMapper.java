package br.com.rony.academico.sistema_academico.mapper;

import br.com.rony.academico.sistema_academico.dto.request.AlunoRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.AlunoResponseDTO;
import br.com.rony.academico.sistema_academico.entity.Aluno;
import br.com.rony.academico.sistema_academico.entity.Curso;
import br.com.rony.academico.sistema_academico.entity.Usuario;

public class AlunoMapper {

    public static Aluno toEntity(AlunoRequestDTO dto, Usuario usuario, Curso curso) {
        return Aluno.builder()
                .usuario(usuario)
                .curso(curso)
                .matricula(dto.getMatricula())
                .status("A")
                .build();
    }

    public static AlunoResponseDTO toDTO(Aluno entity) {
        return AlunoResponseDTO.builder()
                .id(entity.getId())
                .nomeAluno(entity.getUsuario().getNome())
                .emailAluno(entity.getUsuario().getEmail())
                .matricula(entity.getMatricula())
                .nomeCurso(entity.getCurso() != null ? entity.getCurso().getNome() : null)
                .status(entity.getStatus())
                .build();
    }
}
