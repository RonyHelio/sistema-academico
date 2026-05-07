package br.com.rony.academico.sistema_academico.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MensagemChatResponseDTO {

    private Long id;
    private Long chatTurmaId;
    private Long remetenteId;
    private String nomeRemetente;
    private String texto;
    private LocalDateTime dataEnvio;
    private String status;
}
