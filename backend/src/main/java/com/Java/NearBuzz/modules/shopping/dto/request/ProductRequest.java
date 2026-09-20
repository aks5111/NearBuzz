package com.Java.NearBuzz.modules.shopping.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProductRequest(
        @NotNull Long categoryId,
        @NotBlank String title,
        String description,
        @NotBlank String priceLabel,
        List<String> imageUrls,
        @NotNull @Min(0) Integer stockQuantity,
        String location,
        String storeName,
        String storeContactName,
        String storePhone,
        String storeEmail,
        String storePhotoUrl,
        String status) {
}
