package com.Java.NearBuzz.modules.shopping.dto.response;

import com.Java.NearBuzz.modules.shopping.entity.ShoppingProduct;

import java.util.List;

public record ProductResponse(
        Long id,
        String title,
        String description,
        String priceLabel,
        String imageUrl,
        List<String> imageUrls,
        Integer stockQuantity,
        String status,
        CategoryResponse category) {

    public static ProductResponse from(ShoppingProduct product) {
        return new ProductResponse(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPriceLabel(),
                product.getImageUrl(),
                product.getImageUrls(),
                product.getStockQuantity(),
                product.getStatus().name(),
                CategoryResponse.from(product.getCategory()));
    }
}
