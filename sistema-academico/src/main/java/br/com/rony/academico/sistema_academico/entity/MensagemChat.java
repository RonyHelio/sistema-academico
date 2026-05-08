package br.com.rony.academico.sistema_academico.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "msg_chat_mensagem", schema = "academico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MensagemChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "msg_nr_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cht_nr_id", nullable = false)
    private ChatTurma chatTurma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usu_nr_id", nullable = false)
    private Usuario remetente;

    @Column(name = "msg_tx_mensagem", nullable = false, length = 1000)
    private String texto;

    @Column(name = "msg_dt_envio", nullable = false)
    private LocalDateTime dataEnvio;

    @Column(name = "msg_tx_status", nullable = false, length = 1)
    private String status;
}
