package br.com.rony.academico.sistema_academico.mapper;

import br.com.rony.academico.sistema_academico.dto.request.MatriculaTurmaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.MatriculaTurmaResponseDTO;
import br.com.rony.academico.sistema_academico.entity.Aluno;
import br.com.rony.academico.sistema_academico.entity.MatriculaTurma;
import br.com.rony.academico.sistema_academico.entity.Turma;

public class MatriculaTurmaMapper {

    public static MatriculaTurma toEntity(MatriculaTurmaRequestDTO dto, Aluno aluno, Turma turma) {
        return MatriculaTurma.builder()
                .aluno(aluno)
                .turma(turma)
                .status("A")
                .build();
    }

    public static MatriculaTurmaResponseDTO toDTO(MatriculaTurma entity) {
        return MatriculaTurmaResponseDTO.builder()
                .id(entity.getId())
                .alunoId(entity.getAluno().getId())
                .nomeAluno(entity.getAluno().getUsuario().getNome())
                .turmaId(entity.getTurma().getId())
                .descricaoTurma(entity.getTurma().getDescricao())
                .nomeDisciplina(entity.getTurma().getDisciplina().getNome())
                .status(entity.getStatus())
                .build();
    }
}
