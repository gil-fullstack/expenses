package com.hightech.expenses.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDate data;

    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status = TransactionStatus.REALIZADA;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Category categoria;

    @ManyToOne
    @JoinColumn(name = "conta_id", nullable = false)
    private Account conta;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;

    private String observacoes;

    @ElementCollection
    @CollectionTable(name = "transaction_tags", joinColumns = @JoinColumn(name = "transaction_id"))
    @Column(name = "tag")
    @OrderColumn
    private List<String> tags = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (dataCriacao == null) {
            dataCriacao = LocalDateTime.now();
        }
    }

    public record TransactionRequest(
            String descricao,
            BigDecimal valor,
            LocalDate data,
            Long categoriaId,
            Long contaId,
            String observacoes,
            List<String> tags
    ) {}

    public record TransactionResponse(
            Long id,
            String descricao,
            BigDecimal valor,
            LocalDate data,
            String categoria,
            String conta,
            TransactionStatus status,
            List<String> tags
    ) {}

    public enum TransactionStatus {
        PENDENTE, REALIZADA, CANCELADA;

        public boolean isPending() { return this == PENDENTE; }
        public boolean isCompleted() { return this == REALIZADA; }
    }
}
