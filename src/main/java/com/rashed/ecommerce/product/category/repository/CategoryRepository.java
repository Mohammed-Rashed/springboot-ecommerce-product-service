package com.rashed.ecommerce.product.category.repository;

import com.rashed.ecommerce.product.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsBySlug(String slug);

    boolean existsByNameIgnoreCase(String name);

    Optional<Category> findBySlug(String slug);

    Optional<Category> findByIdAndIsActiveTrue(Long id);

}
