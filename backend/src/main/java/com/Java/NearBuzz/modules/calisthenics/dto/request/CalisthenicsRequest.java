package com.Java.NearBuzz.modules.calisthenics.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CalisthenicsRequest(
        @NotBlank String title,
        String description,
        @NotBlank String location,
        String scheduleText,
        String priceLabel,
        String tag,
        String imageUrl,
        Double latitude,
        Double longitude,
        String difficultyLevel,
        String status) {
}
