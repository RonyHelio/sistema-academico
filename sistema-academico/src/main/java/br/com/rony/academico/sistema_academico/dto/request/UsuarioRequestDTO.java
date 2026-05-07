package br.com.rony.academico.sistema_academico.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRequestDTO {

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 150, message = "O nome deve ter no máximo 150 caracteres.")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "E-mail inválido.")
    private String email;

    @NotBlank(message = "O login é obrigatório.")
    private String login;

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;

    @NotBlank(message = "O perfil é obrigatório.")
    private String perfil;
}
