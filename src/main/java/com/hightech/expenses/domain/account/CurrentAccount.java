package com.hightech.expenses.domain.account;

import java.math.BigDecimal;

public record CurrentAccount(String bankName) implements AccountType {
    @Override public String getDescription() { return "Conta Corrente"; }
    @Override public BigDecimal getInterestRate() { return BigDecimal.ZERO; }
}
