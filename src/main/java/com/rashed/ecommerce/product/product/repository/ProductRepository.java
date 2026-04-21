package com.rashed.ecommerce.product.product.repository;

import com.rashed.ecommerce.product.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsBySlug(String slug);

    boolean existsBySku(String sku);

    Optional<Product> findBySlug(String slug);

    Optional<Product> findBySku(String sku);

    Optional<Product> findByIdAndIsActiveTrue(Long id);

    Boolean existsBySlugAndIdNot(String slug, Long id);
    Boolean existsBySkuAndIdNot(String sku, Long id);


    @EntityGraph(attributePaths = {"category"})
    Page<Product> findAllByOrderByIdDesc(Pageable pageable);

}
