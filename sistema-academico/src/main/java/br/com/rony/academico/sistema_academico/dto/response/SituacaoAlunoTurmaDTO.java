package br.com.rony.academico.sistema_academico.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SituacaoAlunoTurmaDTO {

    private Long turmaId;
    private String nomeDisciplina;
    private String descricaoTurma;
    private String periodoLetivo;
    private BigDecimal mediaNotas;
    private Integer totalFaltas;
    private String situacao;
}
