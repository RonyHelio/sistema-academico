package br.com.rony.academico.sistema_academico.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String login;
    private String perfil;
    private LocalDateTime cadastro;
    private String status;
}
