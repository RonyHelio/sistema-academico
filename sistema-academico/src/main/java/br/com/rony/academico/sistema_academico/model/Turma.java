package br.com.rony.academico.sistema_academico.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tur_turma", schema = "academico")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tur_nr_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dis_nr_id", nullable = false)
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "pro_nr_id")
    private Professor professor;

    @Column(name = "pel_nr_id", nullable = false)
    private Long periodoLetivo;

    @Column(name = "tur_tx_descricao", nullable = false)
    private String descricaoTurma;

    @Column(name = "tur_tx_codigo_suap")
    private String codigoSuap;

    @Column(name = "tur_tx_status", nullable = false, length = 1)
    private String status;
}
