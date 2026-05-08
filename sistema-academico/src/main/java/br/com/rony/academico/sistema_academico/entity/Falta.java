package br.com.rony.academico.sistema_academico.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "fal_falta", schema = "academico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Falta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fal_nr_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mtr_nr_id", nullable = false)
    private MatriculaTurma matriculaTurma;

    @Column(name = "fal_dt_data", nullable = false)
    private LocalDate data;

    @Column(name = "fal_nr_quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "fal_tx_observacao")
    private String observacao;

    @Column(name = "fal_tx_status", nullable = false, length = 1)
    private String status;
}
