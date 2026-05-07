package br.com.rony.academico.sistema_academico.mapper;

import br.com.rony.academico.sistema_academico.dto.request.TurmaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.TurmaResponseDTO;
import br.com.rony.academico.sistema_academico.entity.Disciplina;
import br.com.rony.academico.sistema_academico.entity.Professor;
import br.com.rony.academico.sistema_academico.entity.Turma;

/**
 * Mapper responsável pela conversão entre Turma e seus DTOs.
 */
public class TurmaMapper {

    public static Turma toEntity(TurmaRequestDTO dto, Disciplina disciplina, Professor professor) {
        return Turma.builder()
                .disciplina(disciplina)
                .professor(professor)
                .periodoLetivoId(dto.getPeriodoLetivoId())
                .descricao(dto.getDescricao())
                .codigoSuap(dto.getCodigoSuap())
                .status("A")
                .build();
    }

    public static TurmaResponseDTO toDTO(Turma entity) {
        return TurmaResponseDTO.builder()
                .id(entity.getId())
                .nomeDisciplina(entity.getDisciplina().getNome())
                .nomeProfessor(entity.getProfessor() != null ? entity.getProfessor().getUsuario().getNome() : null)
                .periodoLetivoId(entity.getPeriodoLetivoId())
                .descricao(entity.getDescricao())
                .status(entity.getStatus())
                .build();
    }
}
