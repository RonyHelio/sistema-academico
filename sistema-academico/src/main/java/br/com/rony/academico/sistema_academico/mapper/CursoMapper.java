package br.com.rony.academico.sistema_academico.mapper;

import br.com.rony.academico.sistema_academico.dto.request.CursoRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.CursoResponseDTO;
import br.com.rony.academico.sistema_academico.entity.Curso;

/**
 * Mapper responsável pela conversão entre Curso e seus DTOs.
 */
public class CursoMapper {

    public static Curso toEntity(CursoRequestDTO dto) {
        return Curso.builder()
                .nome(dto.getNome())
                .codigoSuap(dto.getCodigoSuap())
                .status("A")
                .build();
    }

    public static CursoResponseDTO toDTO(Curso entity) {
        return CursoResponseDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .codigoSuap(entity.getCodigoSuap())
                .status(entity.getStatus())
                .build();
    }
}
