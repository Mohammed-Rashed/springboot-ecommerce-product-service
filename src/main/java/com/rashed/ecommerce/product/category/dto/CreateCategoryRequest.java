package com.rashed.ecommerce.product.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCategoryRequest(
        @NotBlank(message = "Category name is required")
        String name,

        @Size(max = 500, message = "Description must not exceed 500 characters")
        String description
) {
}