package com.hightech.expenses.repository;

import com.hightech.expenses.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
