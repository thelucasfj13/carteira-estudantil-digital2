package com.carteira.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "estudantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigoCarteira = UUID.randomUUID().toString();

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(nullable = false)
    private String instituicao;

    @Column(nullable = false)
    private String curso;

    @Column(nullable = false)
    private String nivelEnsino;

    @Column(nullable = false, unique = true)
    private String matricula;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private LocalDate validadeCarteira;

    private String fotoUrl;

    private String qrCodeUrl;

    @Enumerated(EnumType.STRING)
    private StatusCarteira status = StatusCarteira.ATIVA;

    private LocalDateTime dataEmissao = LocalDateTime.now();

    private LocalDateTime dataAtualizacao = LocalDateTime.now();

    @Column(columnDefinition = "TEXT")
    private String historicoAlteracoes = "";

    public enum StatusCarteira {
        ATIVA, VENCIDA, CANCELADA
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}