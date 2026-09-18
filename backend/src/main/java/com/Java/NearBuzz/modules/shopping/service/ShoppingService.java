package com.Java.NearBuzz.modules.shopping.service;

import com.Java.NearBuzz.modules.shopping.dto.request.ProductRequest;
import com.Java.NearBuzz.modules.shopping.dto.response.CategoryResponse;
import com.Java.NearBuzz.modules.shopping.dto.response.ProductResponse;

import java.util.List;

public interface ShoppingService {
    List<CategoryResponse> listCategories();

    List<ProductResponse> searchPublishedProducts(String categorySlug, String search);

    List<ProductResponse> listAllProducts();

    ProductResponse createProduct(ProductRequest request, Long createdBy);

    ProductResponse updateProduct(Long id, ProductRequest request);

    void deleteProduct(Long id);
}
