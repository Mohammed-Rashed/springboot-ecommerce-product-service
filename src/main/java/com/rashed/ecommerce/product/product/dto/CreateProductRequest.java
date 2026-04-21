package com.rashed.ecommerce.product.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
public record CreateProductRequest(
        @NotBlank(message = "Product name is required")
        String name,
        @Size(max = 500, message = "Description must not exceed 500 characters")
        String description,
        @NotBlank(message = "Price is required")
        @DecimalMin(value = "0.01", message = "Product price must be greater than 0")
        BigDecimal price,
        @NotBlank(message = "Product sku is required")
        @Size(max = 100, message = "Product sku must not exceed 100 characters")
        String sku,
        String imageUrl,
        @NotNull(message = "Category id is required")
        Long categoryId
) {
}