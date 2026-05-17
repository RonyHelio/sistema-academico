package br.com.rony.academico.sistema_academico.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Table(name = "not_nota", schema = "academico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "not_nr_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mtr_nr_id", nullable = false)
    private MatriculaTurma matriculaTurma;

    @Column(name = "not_tx_descricao", nullable = false)
    private String descricao;

    @Column(name = "not_nr_valor", nullable = false, precision = 5, scale = 2)
    private BigDecimal valor;

    @Column(name = "not_tx_status", nullable = false, length = 1)
    private String status;
}
