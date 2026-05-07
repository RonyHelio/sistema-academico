package br.com.rony.academico.sistema_academico.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FaltaRequestDTO {

    @NotNull(message = "O ID da matrícula na turma é obrigatório.")
    private Long matriculaTurmaId;

    @NotNull(message = "A data da falta é obrigatória.")
    private LocalDate data;

    @NotNull(message = "A quantidade de faltas é obrigatória.")
    @Min(value = 1, message = "A quantidade mínima de faltas é 1.")
    private Integer quantidade;

    private String observacao;
}
