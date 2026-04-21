package com.rashed.ecommerce.product.category.repository;

import com.rashed.ecommerce.product.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsBySlug(String slug);

    boolean existsByNameIgnoreCase(String name);

    Optional<Category> findBySlug(String slug);

    Optional<Category> findByIdAndIsActiveTrue(Long id);

    boolean existsBySlugAndIdNot(String slug, Long id);
}
