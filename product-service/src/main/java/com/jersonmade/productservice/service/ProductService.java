package com.jersonmade.productservice.service;

import com.jersonmade.productservice.dto.ProductRequest;
import com.jersonmade.productservice.dto.ProductResponse;
import com.jersonmade.productservice.model.Product;
import com.jersonmade.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .nameId(productRequest.getNameId())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .categories(productRequest.getCategories())
                .price(productRequest.getPrice())
                .images(productRequest.getImages())
                .build();
        productRepository.save(product);
        log.info("Product with {} is saved" + product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .nameId(product.getNameId())
                .name(product.getName())
                .description(product.getDescription())
                .categories(product.getCategories())
                .price(product.getPrice())
                .images(product.getImages())
                .build();
    }
}
