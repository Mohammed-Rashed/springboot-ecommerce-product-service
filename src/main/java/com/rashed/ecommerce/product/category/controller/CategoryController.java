package com.rashed.ecommerce.product.category.controller;

import com.rashed.ecommerce.product.category.dto.CategoryResponse;
import com.rashed.ecommerce.product.category.dto.CreateCategoryRequest;
import com.rashed.ecommerce.product.category.entity.Category;
import com.rashed.ecommerce.product.category.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public CategoryResponse createCategory(@RequestBody @Valid CreateCategoryRequest request) {
        return categoryService.createCategory(request);
    }

    @PatchMapping("/{id}")
    public CategoryResponse updateCategory(@PathVariable Long id, @RequestBody @Valid CreateCategoryRequest request) {
        return categoryService.updateCategory(id,request);
    }
}
