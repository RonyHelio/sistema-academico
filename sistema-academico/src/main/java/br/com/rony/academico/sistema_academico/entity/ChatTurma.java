package br.com.rony.academico.sistema_academico.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "cht_chat_turma", schema = "academico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatTurma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cht_nr_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tur_nr_id", nullable = false)
    private Turma turma;

    @Column(name = "cht_tx_titulo", length = 150)
    private String titulo;

    @Column(name = "cht_dt_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "cht_tx_status", nullable = false, length = 1)
    private String status;
}
