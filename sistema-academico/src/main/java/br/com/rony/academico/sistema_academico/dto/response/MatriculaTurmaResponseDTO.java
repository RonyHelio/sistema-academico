package br.com.rony.academico.sistema_academico.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatriculaTurmaResponseDTO {

    private Long id;
    private Long alunoId;
    private String nomeAluno;
    private Long turmaId;
    private String descricaoTurma;
    private String nomeDisciplina;
    private String status;
}
