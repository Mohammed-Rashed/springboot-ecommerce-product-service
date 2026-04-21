package com.rashed.ecommerce.product.product.controller;

import com.rashed.ecommerce.product.product.dto.CreateProductRequest;
import com.rashed.ecommerce.product.product.dto.ProductResponse;
import com.rashed.ecommerce.product.product.dto.UpdateProductRequest;
import com.rashed.ecommerce.product.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;


    @PostMapping
    public ProductResponse createProduct(@RequestBody @Valid CreateProductRequest request) {
        return productService.createProduct(request);
    }
    @PatchMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable Long id,@RequestBody @Valid UpdateProductRequest request) {
        return productService.updateProduct(id,request);
    }
}
