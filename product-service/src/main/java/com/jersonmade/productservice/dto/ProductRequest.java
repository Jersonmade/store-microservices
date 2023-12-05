package com.jersonmade.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String nameId;
    private String name;
    private String description;
    private String categories;
    private BigDecimal price;
    private List<String> images;
}
