package com.Java.NearBuzz.modules.shopping.service.impl;

import com.Java.NearBuzz.common.exception.BadRequestException;
import com.Java.NearBuzz.modules.shopping.dto.request.ProductRequest;
import com.Java.NearBuzz.modules.shopping.dto.response.CategoryResponse;
import com.Java.NearBuzz.modules.shopping.dto.response.ProductResponse;
import com.Java.NearBuzz.modules.shopping.entity.ProductStatus;
import com.Java.NearBuzz.modules.shopping.entity.ShoppingCategory;
import com.Java.NearBuzz.modules.shopping.entity.ShoppingProduct;
import com.Java.NearBuzz.modules.shopping.repository.ShoppingCategoryRepository;
import com.Java.NearBuzz.modules.shopping.repository.ShoppingProductRepository;
import com.Java.NearBuzz.modules.shopping.service.ShoppingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    private final ShoppingCategoryRepository categoryRepository;
    private final ShoppingProductRepository productRepository;

    public ShoppingServiceImpl(
            ShoppingCategoryRepository categoryRepository, ShoppingProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<CategoryResponse> listCategories() {
        return categoryRepository.findAll().stream().map(CategoryResponse::from).toList();
    }

    @Override
    public List<ProductResponse> searchPublishedProducts(String categorySlug, String search) {
        String slug = blankToNull(categorySlug);
        String query = blankToNull(search) == null ? null : search.trim().toLowerCase();

        return productRepository.findByStatusOrderByCreatedAtDesc(ProductStatus.PUBLISHED).stream()
                .filter(p -> slug == null || slug.equals(p.getCategory().getSlug()))
                .filter(p -> query == null || p.getTitle().toLowerCase().contains(query))
                .map(ProductResponse::from)
                .toList();
    }

    @Override
    public List<ProductResponse> listAllProducts() {
        return productRepository.findAll().stream().map(ProductResponse::from).toList();
    }

    @Override
    @Transactional
    public ProductResponse createProduct(ProductRequest request, Long createdBy) {
        ShoppingProduct product = new ShoppingProduct();
        applyRequest(product, request);
        product.setCreatedBy(createdBy);
        return ProductResponse.from(productRepository.save(product));
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        ShoppingProduct product = productRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Product not found"));
        applyRequest(product, request);
        return ProductResponse.from(productRepository.save(product));
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new BadRequestException("Product not found");
        }
        productRepository.deleteById(id);
    }

    private void applyRequest(ShoppingProduct product, ProductRequest request) {
        ShoppingCategory category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new BadRequestException("Category not found"));
        product.setCategory(category);
        product.setTitle(request.title());
        product.setDescription(request.description());
        product.setPriceLabel(request.priceLabel());
        product.setImages(request.imageUrls());
        product.setStockQuantity(request.stockQuantity());
        if (request.status() != null) {
            try {
                product.setStatus(ProductStatus.valueOf(request.status()));
            } catch (IllegalArgumentException ex) {
                throw new BadRequestException("Invalid status: " + request.status());
            }
        }
    }

    private String blankToNull(String value) {
        return (value == null || value.isBlank()) ? null : value;
    }
}
