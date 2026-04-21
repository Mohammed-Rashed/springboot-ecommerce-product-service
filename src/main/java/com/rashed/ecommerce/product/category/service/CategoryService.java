package com.rashed.ecommerce.product.category.service;

import com.rashed.ecommerce.product.category.dto.CategoryResponse;
import com.rashed.ecommerce.product.category.dto.CreateCategoryRequest;
import com.rashed.ecommerce.product.category.entity.Category;
import com.rashed.ecommerce.product.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class  CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryResponse createCategory(CreateCategoryRequest request) {
        if (categoryRepository.existsByNameIgnoreCase(request.name())) {
            throw new IllegalArgumentException("Category name already exists");
        }
        String slug = generateUniqueSlug(request.name(),null);

        Category category = Category.builder()
                .name(request.name())
                .slug(slug)
                .description(request.description())
                .isActive(true)
                .build();
        Category savedCategory = categoryRepository.save(category);
        return new CategoryResponse(
                savedCategory.getId(),
                savedCategory.getName(),
                savedCategory.getSlug(),
                savedCategory.getDescription(),
                savedCategory.getIsActive()
        );
    }
    public CategoryResponse updateCategory(Long id, CreateCategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found or inactive"));

        if (!category.getName().equalsIgnoreCase(request.name()) &&
                categoryRepository.existsByNameIgnoreCase(request.name())) {
            throw new IllegalArgumentException("Category name already exists");
        }

        String slug = generateUniqueSlug(request.name(),id);

        category.setName(request.name());
        category.setSlug(slug);
        category.setDescription(request.description());
        Category updatedCategory = categoryRepository.save(category);
        return new CategoryResponse(
                updatedCategory.getId(),
                updatedCategory.getName(),
                updatedCategory.getSlug(),
                updatedCategory.getDescription(),
                updatedCategory.getIsActive()
        );
    }

    private String generateUniqueSlug(String name,Long id) {
        String baseSlug = name.toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("^-|-$", "");
        String slug = baseSlug;
        int counter = 1;
        while (categoryRepository.existsBySlugAndIdNot(slug,id)) {
            slug = baseSlug + "-" + counter++;
        }
        return slug;
    }

}
