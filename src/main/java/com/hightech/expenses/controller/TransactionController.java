package com.hightech.expenses.controller;

import com.hightech.expenses.domain.Transaction;
import com.hightech.expenses.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction.TransactionResponse create(@RequestParam Long userId,
                                                  @RequestBody @Valid Transaction.TransactionRequest req) {
        return transactionService.toResponse(transactionService.create(req, userId));
    }

    @GetMapping
    public List<Transaction.TransactionResponse> list() {
        return transactionService.list().stream().map(transactionService::toResponse).toList();
    }

    @GetMapping("/{id}")
    public Transaction.TransactionResponse get(@PathVariable Long id) {
        return transactionService.toResponse(transactionService.get(id));
    }

    @PutMapping("/{id}")
    public Transaction.TransactionResponse update(@PathVariable Long id,
                                                  @RequestParam(required = false) Long userId,
                                                  @RequestBody @Valid Transaction.TransactionRequest req) {
        return transactionService.toResponse(transactionService.update(id, req, userId));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { transactionService.delete(id); }
}
