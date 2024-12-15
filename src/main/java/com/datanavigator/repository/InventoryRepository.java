package com.datanavigator.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.datanavigator.model.Inventory;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    // Find inventory items by name
    List<Inventory> findByItemName(String itemName);

    // Find inventory items by location
    List<Inventory> findByLocation(String location);

    // Find inventory items with quantity greater than a certain value
    List<Inventory> findByQuantityGreaterThan(int quantity);

    List<Inventory> findByItemNameContainingIgnoreCase(String itemName);
}
