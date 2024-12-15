package com.datanavigator.repository;





import org.springframework.data.jpa.repository.JpaRepository;

import com.datanavigator.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Find products by name
    List<Product> findByName(String name);

    // Find products by price range
    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    // Find products by quantity
    List<Product> findByQuantityGreaterThan(int quantity);

    List<Product> findByNameContainingIgnoreCase(String name);
}
