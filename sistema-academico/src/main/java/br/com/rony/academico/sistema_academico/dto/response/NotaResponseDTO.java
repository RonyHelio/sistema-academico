package br.com.rony.academico.sistema_academico.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotaResponseDTO {

    private Long id;
    private Long matriculaTurmaId;
    private String nomeAluno;
    private String nomeDisciplina;
    private String descricao;
    private BigDecimal valor;
    private String status;
}
