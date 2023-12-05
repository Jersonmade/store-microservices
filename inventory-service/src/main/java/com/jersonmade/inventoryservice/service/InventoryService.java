package com.jersonmade.inventoryservice.service;

import com.jersonmade.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public Boolean isInStock(String name) {
        return inventoryRepository.findByName(name).isPresent();
    }
}
