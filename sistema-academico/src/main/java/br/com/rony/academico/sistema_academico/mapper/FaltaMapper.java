package br.com.rony.academico.sistema_academico.mapper;

import br.com.rony.academico.sistema_academico.dto.request.FaltaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.FaltaResponseDTO;
import br.com.rony.academico.sistema_academico.entity.Falta;
import br.com.rony.academico.sistema_academico.entity.MatriculaTurma;

public class FaltaMapper {

    public static Falta toEntity(FaltaRequestDTO dto, MatriculaTurma matriculaTurma) {
        return Falta.builder()
                .matriculaTurma(matriculaTurma)
                .data(dto.getData())
                .quantidade(dto.getQuantidade())
                .observacao(dto.getObservacao())
                .status("A")
                .build();
    }

    public static FaltaResponseDTO toDTO(Falta entity) {
        return FaltaResponseDTO.builder()
                .id(entity.getId())
                .matriculaTurmaId(entity.getMatriculaTurma().getId())
                .nomeAluno(entity.getMatriculaTurma().getAluno().getUsuario().getNome())
                .nomeDisciplina(entity.getMatriculaTurma().getTurma().getDisciplina().getNome())
                .data(entity.getData())
                .quantidade(entity.getQuantidade())
                .observacao(entity.getObservacao())
                .status(entity.getStatus())
                .build();
    }
}
