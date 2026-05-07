package br.com.rony.academico.sistema_academico.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TurmaRequestDTO {

    @NotNull(message = "O ID da disciplina é obrigatório.")
    private Long disciplinaId;

    @NotNull(message = "O ID do professor é obrigatório.")
    private Long professorId;

    @NotNull(message = "O ID do período letivo é obrigatório.")
    private Long periodoLetivoId;

    private String descricao;

    private String codigoSuap;
}
