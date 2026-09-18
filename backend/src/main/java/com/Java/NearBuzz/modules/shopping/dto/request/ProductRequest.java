package com.Java.NearBuzz.modules.shopping.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequest(
        @NotNull Long categoryId,
        @NotBlank String title,
        String description,
        @NotBlank String priceLabel,
        String imageUrl,
        @NotNull @Min(0) Integer stockQuantity,
        String status) {
}
