package com.rashed.ecommerce.product.category.dto;

public record CategoryResponse(
         Long id,
         String name,
         String slug,
         String description,
         Boolean isActive
) {


}