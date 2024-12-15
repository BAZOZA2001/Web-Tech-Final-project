package com.datanavigator.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.datanavigator.model.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Find employees by name
    List<Employee> findByName(String name);

    // Find employees by role
    List<Employee> findByRole(String role);

    // Find employees by email
    Employee findByEmail(String email);

    List<Employee> findByNameContainingIgnoreCase(String name);
}
