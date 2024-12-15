package com.datanavigator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.datanavigator.model.Customer;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByName(String name);
    Customer findByEmail(String email);
    Customer findByPhone(String phone);
    List<Customer> findByNameContainingIgnoreCase(String name);
}
