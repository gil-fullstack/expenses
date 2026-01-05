package com.hightech.expenses.domain.account;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.math.BigDecimal;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CurrentAccount.class, name = "current"),
        @JsonSubTypes.Type(value = SavingsAccount.class, name = "savings"),
        @JsonSubTypes.Type(value = CreditCardAccount.class, name = "credit"),
        @JsonSubTypes.Type(value = InvestmentAccount.class, name = "investment")
})
public sealed interface AccountType permits
        CurrentAccount, SavingsAccount, CreditCardAccount, InvestmentAccount {
    String getDescription();
    BigDecimal getInterestRate();
}
