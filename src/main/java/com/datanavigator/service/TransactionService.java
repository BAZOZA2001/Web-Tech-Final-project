package com.datanavigator.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.datanavigator.model.Transaction;
import com.datanavigator.repository.TransactionRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Page<Transaction> findAllPaginated(int page, int size) {
        return transactionRepository.findAll(PageRequest.of(page, size));
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    public List<Transaction> findByCustomerId(Long customerId) {
        return transactionRepository.findByCustomerId(customerId);
    }

    public List<Transaction> findByProductId(Long productId) {
        return transactionRepository.findByProductId(productId);
    }

    public List<Transaction> findByDateBetween(Date startDate, Date endDate) {
        return transactionRepository.findByDateBetween(startDate, endDate);
    }

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }

    public List<Transaction> findByAmount(double amount) {
        return transactionRepository.findByAmount(amount);
    }
}
