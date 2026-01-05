package com.hightech.expenses.domain.account;

import java.math.BigDecimal;

public record SavingsAccount(String bankName, BigDecimal interestRate) implements AccountType {
    @Override public String getDescription() { return "Poupança"; }
    @Override public BigDecimal getInterestRate() { return interestRate == null ? BigDecimal.ZERO : interestRate; }
}
