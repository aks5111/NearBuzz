package com.Java.NearBuzz.modules.fitness.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record FitnessRequest(
        @NotBlank String title,
        String description,
        @NotBlank String location,
        String scheduleText,
        String priceLabel,
        String tag,
        List<String> imageUrls,
        Double latitude,
        Double longitude,
        String trainerName,
        String difficultyLevel,
        String gymName,
        String trainerPhone,
        String trainerEmail,
        String trainerPhotoUrl,
        String status) {
}
