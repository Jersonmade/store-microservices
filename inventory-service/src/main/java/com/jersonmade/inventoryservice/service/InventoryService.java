package com.jersonmade.inventoryservice.service;

import com.jersonmade.inventoryservice.dto.InventoryResponse;
import com.jersonmade.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public List<InventoryResponse> isInStock(List<String> name) {
        return inventoryRepository.findByScuCodeIn(name).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .scuCode(inventory.getScuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                ).toList();
    }
}
