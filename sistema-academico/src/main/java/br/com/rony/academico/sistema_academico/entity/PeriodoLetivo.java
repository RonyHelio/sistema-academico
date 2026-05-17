package br.com.rony.academico.sistema_academico.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pel_periodo_letivo", schema = "academico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeriodoLetivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pel_nr_id")
    private Long id;

    @Column(name = "pel_nr_ano", nullable = false)
    private Integer ano;

    @Column(name = "pel_nr_semestre", nullable = false)
    private Integer semestre;

    @Column(name = "pel_tx_descricao", length = 50)
    private String descricao;

    @Column(name = "pel_tx_status", nullable = false, length = 1)
    private String status;
}
