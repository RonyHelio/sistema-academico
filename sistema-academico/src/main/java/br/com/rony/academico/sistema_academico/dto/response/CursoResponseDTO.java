package br.com.rony.academico.sistema_academico.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CursoResponseDTO {

    private Long id;
    private String nome;
    private String codigoSuap;
    private String status;
}
