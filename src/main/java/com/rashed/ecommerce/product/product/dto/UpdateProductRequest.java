package com.rashed.ecommerce.product.product.dto;

import java.math.BigDecimal;
public record UpdateProductRequest(
        String name,
        String description,
        BigDecimal price,
        String sku,
        String imageUrl,
        Long categoryId,
        Boolean isActive
) {

}
