package br.com.rony.academico.sistema_academico.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FaltaResponseDTO {

    private Long id;
    private Long matriculaTurmaId;
    private String nomeAluno;
    private String nomeDisciplina;
    private LocalDate data;
    private Integer quantidade;
    private String observacao;
    private String status;
}
