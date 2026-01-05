package com.hightech.expenses.domain.account;

import java.math.BigDecimal;

public record InvestmentAccount(String broker, BigDecimal expectedRate) implements AccountType {
    @Override public String getDescription() { return "Investimento"; }
    @Override public BigDecimal getInterestRate() { return expectedRate == null ? BigDecimal.ZERO : expectedRate; }
}
