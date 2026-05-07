package br.com.rony.academico.sistema_academico.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CursoRequestDTO {

    @NotBlank(message = "O nome do curso é obrigatório.")
    private String nome;

    private String codigoSuap;
}
