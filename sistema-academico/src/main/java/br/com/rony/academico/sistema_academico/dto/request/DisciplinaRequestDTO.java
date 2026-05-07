package br.com.rony.academico.sistema_academico.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DisciplinaRequestDTO {

    @NotBlank(message = "O nome da disciplina é obrigatório.")
    private String nome;

    private String codigo;

    private String codigoSuap;

    @NotNull(message = "A carga horária é obrigatória.")
    private Integer cargaHoraria;
}
