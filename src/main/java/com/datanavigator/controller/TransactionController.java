package com.datanavigator.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.datanavigator.model.Transaction;
import com.datanavigator.service.TransactionService;

import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/paginated")
    public Page<Transaction> getTransactionsPaginated(@RequestParam int page, @RequestParam int size) {
        return transactionService.findAllPaginated(page, size);
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionService.findById(id).orElse(null);
    }

    @GetMapping("/byCustomer")
    public List<Transaction> getTransactionsByCustomer(@RequestParam Long customerId) {
        return transactionService.findByCustomerId(customerId);
    }

    @GetMapping("/byProduct")
    public List<Transaction> getTransactionsByProduct(@RequestParam Long productId) {
        return transactionService.findByProductId(productId);
    }

    @GetMapping("/byDate")
    public List<Transaction> getTransactionsByDate(@RequestParam Date startDate, @RequestParam Date endDate) {
        return transactionService.findByDateBetween(startDate, endDate);
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.save(transaction);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        transaction.setId(id);
        return transactionService.save(transaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteById(id);
    }

    @GetMapping("/search")
    public List<Transaction> searchTransactions(@RequestParam double amount) {
        return transactionService.findByAmount(amount);
    }
    
}
