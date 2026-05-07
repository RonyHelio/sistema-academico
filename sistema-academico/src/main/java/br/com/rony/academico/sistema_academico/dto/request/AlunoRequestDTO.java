package br.com.rony.academico.sistema_academico.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlunoRequestDTO {

    @NotNull(message = "O ID do usuário é obrigatório.")
    private Long usuarioId;

    @NotNull(message = "O ID do curso é obrigatório.")
    private Long cursoId;

    @NotBlank(message = "A matrícula é obrigatória.")
    private String matricula;
}
