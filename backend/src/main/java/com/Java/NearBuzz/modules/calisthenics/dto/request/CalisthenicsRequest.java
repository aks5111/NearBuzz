package com.Java.NearBuzz.modules.calisthenics.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CalisthenicsRequest(
        @NotBlank String title,
        String description,
        @NotBlank String location,
        String scheduleText,
        String priceLabel,
        String tag,
        List<String> imageUrls,
        Double latitude,
        Double longitude,
        String difficultyLevel,
        String gymName,
        String trainerContactName,
        String trainerPhone,
        String trainerEmail,
        String trainerPhotoUrl,
        String status) {
}
