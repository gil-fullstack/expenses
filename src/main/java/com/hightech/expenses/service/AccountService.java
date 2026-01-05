package com.hightech.expenses.service;

import com.hightech.expenses.domain.Account;
import com.hightech.expenses.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account create(Account a) {
        return accountRepository.save(a);
    }

    @Transactional(readOnly = true)
    public List<Account> list() { return accountRepository.findAll(); }

    @Transactional(readOnly = true)
    public Account get(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));
    }

    public Account update(Long id, Account a) {
        Account existing = get(id);
        existing.setNome(a.getNome());
        existing.setSaldoInicial(a.getSaldoInicial());
        existing.setSaldoAtual(a.getSaldoAtual());
        existing.setTipoConta(a.getTipoConta());
        existing.setInstituicao(a.getInstituicao());
        existing.setUsuario(a.getUsuario());
        existing.setAtiva(a.isAtiva());
        return accountRepository.save(existing);
    }

    public void delete(Long id) {
        Account a = get(id);
        a.setAtiva(false);
        accountRepository.save(a);
    }
}
