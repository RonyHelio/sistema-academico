package br.com.rony.academico.sistema_academico.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pro_professor", schema = "academico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_nr_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usu_nr_id", nullable = false)
    private Usuario usuario;

    @Column(name = "pro_tx_matricula_siape")
    private String matriculaSiape;

    @Column(name = "pro_tx_status", nullable = false, length = 1)
    private String status;
}
