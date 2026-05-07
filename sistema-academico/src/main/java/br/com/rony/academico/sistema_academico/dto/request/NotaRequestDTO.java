package br.com.rony.academico.sistema_academico.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotaRequestDTO {

    @NotNull(message = "O ID da matrícula na turma é obrigatório.")
    private Long matriculaTurmaId;

    @NotBlank(message = "A descrição da nota é obrigatória.")
    private String descricao;

    @NotNull(message = "O valor da nota é obrigatório.")
    @DecimalMin(value = "0.0", message = "A nota mínima é 0.")
    @DecimalMax(value = "100.0", message = "A nota máxima é 100.")
    private java.math.BigDecimal valor;
}
