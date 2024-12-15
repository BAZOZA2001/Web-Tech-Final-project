package com.datanavigator.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.datanavigator.model.Inventory;
import com.datanavigator.service.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/paginated")
    public Page<Inventory> getInventoryPaginated(@RequestParam int page, @RequestParam int size) {
        return inventoryService.findAllPaginated(page, size);
    }

    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryService.findAll();
    }

    @GetMapping("/{id}")
    public Inventory getInventoryById(@PathVariable Long id) {
        return inventoryService.findById(id).orElse(null);
    }

    @GetMapping("/search")
    public List<Inventory> searchInventoryByItemName(@RequestParam String itemName) {
        return inventoryService.findByItemName(itemName);
    }

    @PostMapping
    public Inventory createInventory(@RequestBody Inventory inventory) {
        return inventoryService.save(inventory);
    }

    @PutMapping("/{id}")
    public Inventory updateInventory(@PathVariable Long id, @RequestBody Inventory inventory) {
        inventory.setId(id);
        return inventoryService.save(inventory);
    }

    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable Long id) {
        inventoryService.deleteById(id);
    }

    @GetMapping("/search")
public List<Inventory> searchInventory(@RequestParam String itemName) {
    return inventoryService.findByItemName(itemName);
}

}
