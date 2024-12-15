package com.datanavigator.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datanavigator.model.Customer;
import com.datanavigator.model.Employee;
import com.datanavigator.model.Inventory;
import com.datanavigator.model.Product;
import com.datanavigator.model.Transaction;
import com.datanavigator.repository.CustomerRepository;
import com.datanavigator.repository.EmployeeRepository;
import com.datanavigator.repository.InventoryRepository;
import com.datanavigator.repository.ProductRepository;
import com.datanavigator.repository.TransactionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GlobalSearchService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public Map<String, Object> search(String keyword) {
        Map<String, Object> results = new HashMap<>();

        List<Customer> customers = customerRepository.findByNameContainingIgnoreCase(keyword);
        List<Product> products = productRepository.findByNameContainingIgnoreCase(keyword);
        
        // Parse the keyword as a double for the amount search
        double amount = 0.0;
        try {
            amount = Double.parseDouble(keyword);
        } catch (NumberFormatException e) {
            // Handle the exception if needed
        }
        List<Transaction> transactions = transactionRepository.findByAmount(amount);
        
        List<Employee> employees = employeeRepository.findByNameContainingIgnoreCase(keyword);
        List<Inventory> inventoryItems = inventoryRepository.findByItemNameContainingIgnoreCase(keyword);

        results.put("customers", customers);
        results.put("products", products);
        results.put("transactions", transactions);
        results.put("employees", employees);
        results.put("inventoryItems", inventoryItems);

        return results;
    }
}
