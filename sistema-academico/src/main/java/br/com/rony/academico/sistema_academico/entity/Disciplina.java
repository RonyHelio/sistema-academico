package br.com.rony.academico.sistema_academico.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidade que representa uma disciplina no sistema acadêmico.
 * Responsável por armazenar dados de disciplinas ofertadas.
 */
@Entity
@Table(name = "dis_disciplina", schema = "academico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dis_nr_id")
    private Long id;

    @Column(name = "dis_tx_nome", nullable = false)
    private String nome;

    @Column(name = "dis_tx_codigo")
    private String codigo;

    @Column(name = "dis_tx_codigo_suap")
    private String codigoSuap;

    @Column(name = "dis_nr_carga_horaria")
    private Integer cargaHoraria;

    @Column(name = "dis_tx_status", nullable = false, length = 1)
    private String status;
}
