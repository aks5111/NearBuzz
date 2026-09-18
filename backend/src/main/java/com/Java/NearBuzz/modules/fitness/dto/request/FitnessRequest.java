package com.Java.NearBuzz.modules.fitness.dto.request;

import jakarta.validation.constraints.NotBlank;

public record FitnessRequest(
        @NotBlank String title,
        String description,
        @NotBlank String location,
        String scheduleText,
        String priceLabel,
        String tag,
        String imageUrl,
        Double latitude,
        Double longitude,
        String trainerName,
        String difficultyLevel,
        String status) {
}
