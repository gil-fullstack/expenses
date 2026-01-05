package com.hightech.expenses.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String descricao;
    private String cor;
    private String icone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType tipo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @Builder.Default
    private boolean padrao = false;

    public enum TransactionType {
        RECEITA, DESPESA;

        public boolean isReceita() {
            return this == RECEITA;
        }

        public boolean isDespesa() {
            return this == DESPESA;
        }
    }
}
