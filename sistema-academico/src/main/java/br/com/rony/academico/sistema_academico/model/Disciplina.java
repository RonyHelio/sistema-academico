package br.com.rony.academico.sistema_academico.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dis_disciplina", schema = "academico")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dis_nr_id", nullable = false)
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
