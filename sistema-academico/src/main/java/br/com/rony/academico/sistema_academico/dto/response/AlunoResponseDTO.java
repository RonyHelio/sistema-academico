package br.com.rony.academico.sistema_academico.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlunoResponseDTO {

    private Long id;
    private String nomeAluno;
    private String emailAluno;
    private String matricula;
    private String nomeCurso;
    private String status;
}
