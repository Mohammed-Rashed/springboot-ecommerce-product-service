package com.rashed.ecommerce.product.product.repository;

import com.rashed.ecommerce.product.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsBySlug(String slug);

    boolean existsBySku(String sku);

    Optional<Product> findBySlug(String slug);

    Optional<Product> findBySku(String sku);

    Optional<Product> findByIdAndIsActiveTrue(Long id);

}
