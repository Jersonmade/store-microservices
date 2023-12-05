package com.jersonmade.inventoryservice.controller;


import com.jersonmade.inventoryservice.dto.InventoryResponse;
import com.jersonmade.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> scuCode) {
        return inventoryService.isInStock(scuCode);
    }
}
