package com.rashed.ecommerce.product.product.service;

import com.rashed.ecommerce.product.category.entity.Category;
import com.rashed.ecommerce.product.category.repository.CategoryRepository;
import com.rashed.ecommerce.product.product.dto.CreateProductRequest;
import com.rashed.ecommerce.product.product.dto.ProductResponse;
import com.rashed.ecommerce.product.product.dto.UpdateProductRequest;
import com.rashed.ecommerce.product.product.entity.Product;
import com.rashed.ecommerce.product.product.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.Normalizer;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductResponse createProduct(@RequestBody @Valid CreateProductRequest request) {
        if(productRepository.existsBySku(request.sku())){
            throw new IllegalArgumentException("Product sku already exists");
        }
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        String slug = generateUniqueSlug(request.name(),null);

        Product product = Product.builder()
                .name(request.name())
                .slug(slug)
                .description(request.description())
                .price(request.price())
                .sku(request.sku())
                .imageUrl(request.imageUrl())
                .isActive(true)
                .category(category)
                .build();
        Product savedProduct = productRepository.save(product);

        return mapResponse(savedProduct);
    }
    public ProductResponse updateProduct(Long id,@RequestBody @Valid UpdateProductRequest request) {

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        Product product=productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product Not exists"));
        if (productRepository.existsBySkuAndIdNot(request.sku(), id)) {
            throw new IllegalArgumentException("Product sku already exists");
        }



        String slug = generateUniqueSlug(request.name(), id);

        product.setName(request.name());
        product.setSlug(slug);
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setSku(request.sku());
        product.setImageUrl(request.imageUrl());
        product.setIsActive(request.isActive());
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        return mapResponse(savedProduct);
    }
    private ProductResponse mapResponse(Product product){
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getSlug(),
                product.getDescription(),
                product.getPrice(),
                product.getSku(),
                product.getImageUrl(),
                product.getIsActive(),
                product.getCategory().getId()
        );
    }
    private String generateUniqueSlug(String name,Long id) {
        String baseSlug = toSlug(name);
        String slug = baseSlug;
        int counter = 1;

        while (productRepository.existsBySlugAndIdNot(slug,id)) {
            slug = baseSlug + "-" + counter;
            counter++;
        }

        return slug;
    }

    private String toSlug(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        return normalized
                .toLowerCase(Locale.ROOT)
                .trim()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-{2,}", "-");
    }
    @Transactional(readOnly = true)
    public Page<ProductResponse> getProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return productRepository.findAllByOrderByIdDesc(pageable)
                .map(this::mapResponse);

    }
}
