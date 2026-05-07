package br.com.rony.academico.sistema_academico.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MensagemChatResponseDTO {
    private Long id;
    private Long chatTurmaId;
    private Long remetenteID;
    private String texto;
    private LocalDateTime dataEnvio;
    private String status;
}
