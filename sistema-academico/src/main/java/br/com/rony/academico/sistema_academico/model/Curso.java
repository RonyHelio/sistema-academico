package br.com.rony.academico.sistema_academico.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cur_curso", schema = "academico")

public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cur_nr_id")
    private long id;


    @Column(name = "cur_tx_nome", nullable = false)
    private String nomeCurso;


    @Column(name = "cur_tx_codigo_suap")
    private String codigoSuap;

    @Column(name = "cur_tx_status", nullable = false)
    private String status;
}
