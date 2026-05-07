package br.com.rony.academico.sistema_academico.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidade que representa um aluno no sistema acadêmico.
 * Responsável por vincular um usuário a um curso com matrícula.
 * Relacionamentos: OneToOne com Usuario, ManyToOne com Curso.
 */
@Entity
@Table(name = "alu_aluno", schema = "academico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alu_nr_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usu_nr_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cur_nr_id")
    private Curso curso;

    @Column(name = "alu_tx_matricula", nullable = false, unique = true)
    private String matricula;

    @Column(name = "alu_tx_status", nullable = false, length = 1)
    private String status;
}
