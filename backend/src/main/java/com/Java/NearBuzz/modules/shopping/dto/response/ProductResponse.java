package com.Java.NearBuzz.modules.shopping.dto.response;

import com.Java.NearBuzz.modules.shopping.entity.ShoppingProduct;

public record ProductResponse(
        Long id,
        String title,
        String description,
        String priceLabel,
        String imageUrl,
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
                product.getStockQuantity(),
                product.getStatus().name(),
                CategoryResponse.from(product.getCategory()));
    }
}
