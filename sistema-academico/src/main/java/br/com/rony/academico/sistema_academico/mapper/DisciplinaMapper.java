package br.com.rony.academico.sistema_academico.mapper;

import br.com.rony.academico.sistema_academico.dto.request.DisciplinaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.DisciplinaResponseDTO;
import br.com.rony.academico.sistema_academico.entity.Disciplina;

public class DisciplinaMapper {

    public static Disciplina toEntity(DisciplinaRequestDTO dto) {
        return Disciplina.builder()
                .nome(dto.getNome())
                .codigo(dto.getCodigo())
                .codigoSuap(dto.getCodigoSuap())
                .cargaHoraria(dto.getCargaHoraria())
                .status("A")
                .build();
    }

    public static DisciplinaResponseDTO toDTO(Disciplina entity) {
        return DisciplinaResponseDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .codigo(entity.getCodigo())
                .codigoSuap(entity.getCodigoSuap())
                .cargaHoraria(entity.getCargaHoraria())
                .status(entity.getStatus())
                .build();
    }
}
