package br.com.rony.academico.sistema_academico.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cur_curso", schema = "academico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cur_nr_id")
    private Long id;

    @Column(name = "cur_tx_nome", nullable = false)
    private String nome;

    @Column(name = "cur_tx_codigo_suap")
    private String codigoSuap;

    @Column(name = "cur_tx_status", nullable = false, length = 1)
    private String status;
}
