package com.hightech.expenses.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hightech.expenses.domain.account.AccountType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "contas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal saldoInicial = BigDecimal.ZERO;

    @Column(precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal saldoAtual = BigDecimal.ZERO;

    @Convert(converter = AccountTypeConverter.class)
    private AccountType tipoConta;

    private String instituicao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;

    @Builder.Default
    private boolean ativa = true;

    @Converter
    public static class AccountTypeConverter implements AttributeConverter<AccountType, String> {
        private static final ObjectMapper mapper = new ObjectMapper();
        @Override
        public String convertToDatabaseColumn(AccountType attribute) {
            if (attribute == null) return null;
            try {
                return mapper.writeValueAsString(attribute);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public AccountType convertToEntityAttribute(String dbData) {
            if (dbData == null) return null;
            try {
                return mapper.readValue(dbData, AccountType.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
