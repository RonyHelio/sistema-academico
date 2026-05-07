package br.com.rony.academico.sistema_academico.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TurmaResponseDTO {

    private Long id;
    private String nomeDisciplina;
    private String nomeProfessor;
    private Long periodoLetivoId;
    private String descricao;
    private String status;
}
