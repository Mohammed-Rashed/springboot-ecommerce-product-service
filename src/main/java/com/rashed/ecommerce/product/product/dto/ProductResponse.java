package com.rashed.ecommerce.product.product.dto;


import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String slug,
        String description,
        BigDecimal price,
        String sku,
        String imageUrl,
        Boolean isActive,
        Long categoryId,
        String categoryName
) {
}