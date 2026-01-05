package com.hightech.expenses.service;

import com.hightech.expenses.domain.Account;
import com.hightech.expenses.domain.Category;
import com.hightech.expenses.domain.Transaction;
import com.hightech.expenses.domain.User;
import com.hightech.expenses.repository.AccountRepository;
import com.hightech.expenses.repository.CategoryRepository;
import com.hightech.expenses.repository.TransactionRepository;
import com.hightech.expenses.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              CategoryRepository categoryRepository,
                              AccountRepository accountRepository,
                              UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public Transaction create(Transaction.TransactionRequest req, Long userId) {
        Category categoria = categoryRepository.findById(req.categoriaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inválida"));
        Account conta = accountRepository.findById(req.contaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Conta inválida"));
        User usuario = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário inválido"));

        Transaction t = Transaction.builder()
                .descricao(req.descricao())
                .valor(req.valor())
                .data(req.data())
                .categoria(categoria)
                .conta(conta)
                .usuario(usuario)
                .observacoes(req.observacoes())
                .tags(req.tags())
                .status(Transaction.TransactionStatus.REALIZADA)
                .build();
        return transactionRepository.save(t);
    }

    @Transactional(readOnly = true)
    public List<Transaction> list() { return transactionRepository.findAll(); }

    @Transactional(readOnly = true)
    public Transaction get(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transação não encontrada"));
    }

    public Transaction update(Long id, Transaction.TransactionRequest req, Long userId) {
        Transaction existing = get(id);
        if (req.descricao() != null) existing.setDescricao(req.descricao());
        if (req.valor() != null) existing.setValor(req.valor());
        if (req.data() != null) existing.setData(req.data());
        if (req.observacoes() != null) existing.setObservacoes(req.observacoes());
        if (req.tags() != null) existing.setTags(req.tags());
        if (req.categoriaId() != null) {
            Category categoria = categoryRepository.findById(req.categoriaId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inválida"));
            existing.setCategoria(categoria);
        }
        if (req.contaId() != null) {
            Account conta = accountRepository.findById(req.contaId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Conta inválida"));
            existing.setConta(conta);
        }
        if (userId != null) {
            User usuario = userRepository.findById(userId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário inválido"));
            existing.setUsuario(usuario);
        }
        return transactionRepository.save(existing);
    }

    public void delete(Long id) {
        Transaction t = get(id);
        transactionRepository.delete(t);
    }

    public Transaction.TransactionResponse toResponse(Transaction t) {
        return new Transaction.TransactionResponse(
                t.getId(),
                t.getDescricao(),
                t.getValor(),
                t.getData(),
                t.getCategoria() != null ? t.getCategoria().getNome() : null,
                t.getConta() != null ? t.getConta().getNome() : null,
                t.getStatus(),
                t.getTags()
        );
    }
}
