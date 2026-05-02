package br.com.rony.academico.sistema_academico.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "usu_usuario", schema = "academico")

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_nr_id")
    private Long id;

    @Column(name = "usu_tx_nome", nullable = false)
    private String nome;

    @Column(name = "usu_tx_email", nullable = false, unique = true)
    private String email;

    @Column(name = "usu_tx_perfil", nullable = false)
    private String perfil;

    @Column(name = "usu_dt_cadastro")
    private LocalDateTime cadastro = LocalDateTime.now();

    
}
