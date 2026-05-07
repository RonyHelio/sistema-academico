package br.com.rony.academico.sistema_academico.mapper;

import br.com.rony.academico.sistema_academico.dto.request.NotaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.NotaResponseDTO;
import br.com.rony.academico.sistema_academico.entity.MatriculaTurma;
import br.com.rony.academico.sistema_academico.entity.Nota;

/**
 * Mapper responsável pela conversão entre Nota e seus DTOs.
 */
public class NotaMapper {

    public static Nota toEntity(NotaRequestDTO dto, MatriculaTurma matriculaTurma) {
        return Nota.builder()
                .matriculaTurma(matriculaTurma)
                .descricao(dto.getDescricao())
                .valor(dto.getValor())
                .status("A")
                .build();
    }

    public static NotaResponseDTO toDTO(Nota entity) {
        return NotaResponseDTO.builder()
                .id(entity.getId())
                .matriculaTurmaId(entity.getMatriculaTurma().getId())
                .nomeAluno(entity.getMatriculaTurma().getAluno().getUsuario().getNome())
                .nomeDisciplina(entity.getMatriculaTurma().getTurma().getDisciplina().getNome())
                .descricao(entity.getDescricao())
                .valor(entity.getValor())
                .status(entity.getStatus())
                .build();
    }
}
