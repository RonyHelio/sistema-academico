package br.com.rony.academico.sistema_academico.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeriodoLetivoResponseDTO {

    private Long id;
    private Integer ano;
    private Integer semestre;
    private String descricao;
    private String status;
}
