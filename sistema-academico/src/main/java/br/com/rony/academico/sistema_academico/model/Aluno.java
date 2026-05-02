package br.com.rony.academico.sistema_academico.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "alu_aluno", schema = "academico")

public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alu_nr_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "usu_nr_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "cur_nr_id")
    private Curso curso;

    @Column(name = "alu_tx_matricula", nullable = false, unique = true)
    private String matricula;

    @Column(name = "alu_tx_status")
    private String status; //'A' para ativo, conforme o SQL

}
