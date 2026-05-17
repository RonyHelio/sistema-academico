package br.com.rony.academico.sistema_academico.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatriculaTurmaRequestDTO {

    @NotNull(message = "O ID do aluno é obrigatório.")
    private Long alunoId;

    @NotNull(message = "O ID da turma é obrigatório.")
    private Long turmaId;
}
