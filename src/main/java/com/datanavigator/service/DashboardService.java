package com.datanavigator.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datanavigator.repository.CustomerRepository;
import com.datanavigator.repository.ProductRepository;
import com.datanavigator.repository.TransactionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public long getTotalCustomers() {
        return customerRepository.count();
    }

    public long getTotalProducts() {
        return productRepository.count();
    }

    public long getTotalTransactions() {
        return transactionRepository.count();
    }

    public List<String> getRecentActivities() {
        // Example: Fetching the latest 5 transactions
        return transactionRepository.findTop5ByOrderByDateDesc().stream()
            .map(transaction -> "Transaction ID: " + transaction.getId() + ", Amount: $" + transaction.getAmount())
            .collect(Collectors.toList());
    }
}
