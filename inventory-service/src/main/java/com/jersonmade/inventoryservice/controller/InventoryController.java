package com.jersonmade.inventoryservice.controller;


import com.jersonmade.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean isInStock(@PathVariable("name") String name) {
        return inventoryService.isInStock(name);
    }
}
