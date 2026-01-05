package com.hightech.expenses.domain.account;

import java.math.BigDecimal;

public record CreditCardAccount(String issuer, BigDecimal limit) implements AccountType {
    @Override public String getDescription() { return "Cartão de Crédito"; }
    @Override public BigDecimal getInterestRate() { return new BigDecimal("0.15"); }
}
