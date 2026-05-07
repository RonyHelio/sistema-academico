package br.com.rony.academico.sistema_academico.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DisciplinaResponseDTO {

    private Long id;
    private String nome;
    private String codigo;
    private String codigoSuap;
    private Integer cargaHoraria;
    private String status;
}
