package com.rashed.ecommerce.product.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
public record CreateProductRequest(
        @NotBlank(message = "Product name is required")
        String name,
        @Size(max = 500, message = "Description must not exceed 500 characters")
        String description,
        @NotBlank(message = "Price is required")
        @NumberFormat(style = NumberFormat.Style.NUMBER)
        BigDecimal price,
        String sku,
        String imageUrl,
        Long categoryId
) {
}