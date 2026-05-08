package br.com.rony.academico.sistema_academico.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tur_turma", schema = "academico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tur_nr_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dis_nr_id", nullable = false)
    private Disciplina disciplina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_nr_id")
    private Professor professor;

    @Column(name = "pel_nr_id", nullable = false)
    private Long periodoLetivoId;

    @Column(name = "tur_tx_descricao", nullable = false)
    private String descricao;

    @Column(name = "tur_tx_codigo_suap")
    private String codigoSuap;

    @Column(name = "tur_tx_status", nullable = false, length = 1)
    private String status;
}
