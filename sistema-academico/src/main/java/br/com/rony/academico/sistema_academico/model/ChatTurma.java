package br.com.rony.academico.sistema_academico.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "cht_chat_turma", schema = "academico")

public class ChatTurma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cht_nr_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tur_nr_id", nullable = false)
    private Turma turma;

    @Column(name = "cht_tx_titulo", length = 150)
    private String titulo;

    @Column(name = "cht_dt_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "cht_tx_status", length = 1)
    private String status = "A";
}
