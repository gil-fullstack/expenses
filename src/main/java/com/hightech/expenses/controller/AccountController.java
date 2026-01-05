package com.hightech.expenses.controller;

import com.hightech.expenses.domain.Account;
import com.hightech.expenses.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account create(@RequestBody Account a) { return accountService.create(a); }

    @GetMapping
    public List<Account> list() { return accountService.list(); }

    @GetMapping("/{id}")
    public Account get(@PathVariable Long id) { return accountService.get(id); }

    @PutMapping("/{id}")
    public Account update(@PathVariable Long id, @RequestBody Account a) { return accountService.update(id, a); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { accountService.delete(id); }
}
