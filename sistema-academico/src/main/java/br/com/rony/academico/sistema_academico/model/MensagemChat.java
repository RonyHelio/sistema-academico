package br.com.rony.academico.sistema_academico.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "msg_chat_mensagem", schema = "academico")
public class MensagemChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "msg_nr_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cht_nr_id", nullable = false)
    private ChatTurma chatTurma;

    @ManyToOne
    @JoinColumn(name = "usu_nr_id", nullable = false)
    private Usuario remetente;

    @Column(name = "msg_tx_mensagem", nullable = false, length = 1000)
    private String texto;

    @Column(name = "msg_dt_envio", nullable = false)
    private LocalDateTime dataEnvio;

    @Column(name = "msg_tx_status", nullable = false, length = 1)
    private String status;
}
