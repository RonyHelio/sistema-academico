package br.com.rony.academico.sistema_academico.mapper;

import br.com.rony.academico.sistema_academico.dto.request.PeriodoLetivoRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.PeriodoLetivoResponseDTO;
import br.com.rony.academico.sistema_academico.entity.PeriodoLetivo;

public class PeriodoLetivoMapper {

    public static PeriodoLetivo toEntity(PeriodoLetivoRequestDTO dto) {
        return PeriodoLetivo.builder()
                .ano(dto.getAno())
                .semestre(dto.getSemestre())
                .descricao(dto.getDescricao())
                .status("A")
                .build();
    }

    public static PeriodoLetivoResponseDTO toDTO(PeriodoLetivo entity) {
        return PeriodoLetivoResponseDTO.builder()
                .id(entity.getId())
                .ano(entity.getAno())
                .semestre(entity.getSemestre())
                .descricao(entity.getDescricao())
                .status(entity.getStatus())
                .build();
    }
}
