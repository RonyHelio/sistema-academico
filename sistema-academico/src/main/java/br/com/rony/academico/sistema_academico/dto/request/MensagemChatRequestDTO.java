package br.com.rony.academico.sistema_academico.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MensagemChatRequestDTO {

    @NotNull(message = "O ID do chat da turma é obrigatório.")
    private Long chatTurmaId;

    @NotNull(message = "O ID do remetente é obrigatório.")
    private Long remetenteId;

    @NotBlank(message = "A mensagem não pode estar em branco.")
    @Size(max = 1000, message = "A mensagem ultrapassou o limite de 1000 caracteres.")
    private String texto;
}
