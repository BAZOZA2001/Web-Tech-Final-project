package com.datanavigator.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.datanavigator.model.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Find transactions by customer
    List<Transaction> findByCustomerId(Long customerId);

    // Find transactions by product
    List<Transaction> findByProductId(Long productId);

    // Find transactions by date range
    List<Transaction> findByDateBetween(Date startDate, Date endDate);

     List<Transaction> findTop5ByOrderByDateDesc();

     List<Transaction> findByAmount(double amount);
}
