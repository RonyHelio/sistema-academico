package br.com.rony.academico.sistema_academico.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "usu_usuario", schema = "academico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_nr_id")
    private Long id;

    @Column(name = "usu_tx_nome", nullable = false)
    private String nome;

    @Column(name = "usu_tx_email", nullable = false, unique = true)
    private String email;

    @Column(name = "usu_tx_login", nullable = false, unique = true)
    private String login;

    @Column(name = "usu_tx_senha", nullable = false)
    private String senha;

    @Column(name = "usu_tx_perfil", nullable = false)
    private String perfil;

    @Column(name = "usu_dt_cadastro")
    private LocalDateTime cadastro;

    @Column(name = "usu_tx_status", nullable = false, length = 1)
    private String status;
}
