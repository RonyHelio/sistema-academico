package br.com.rony.academico.sistema_academico.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeriodoLetivoRequestDTO {

    @NotNull(message = "O ano é obrigatório.")
    private Integer ano;

    @NotNull(message = "O semestre é obrigatório.")
    private Integer semestre;

    private String descricao;
}
