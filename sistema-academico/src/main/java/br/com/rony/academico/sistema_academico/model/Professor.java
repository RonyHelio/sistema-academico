package br.com.rony.academico.sistema_academico.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "pro_professor", schema = "academico")

public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_nr_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "usu_nr_id", nullable = false)
    private Usuario usuario;

    @Column(name = "pro_tx_matricula_siape")
    private String matriculaSiape;

    @Column(name = "pro_tx_suap_id")
    private String suapId;

    @Column(name = "pro_tx_status", nullable = false, length = 1)
    private String status;
}
