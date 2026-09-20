package com.Java.NearBuzz.modules.fitness.dto.response;

import com.Java.NearBuzz.modules.fitness.entity.Fitness;

import java.util.List;

public record FitnessResponse(
        Long id,
        String title,
        String description,
        String location,
        String scheduleText,
        String priceLabel,
        String tag,
        String imageUrl,
        List<String> imageUrls,
        Double latitude,
        Double longitude,
        String trainerName,
        String difficultyLevel,
        String status) {

    public static FitnessResponse from(Fitness entity) {
        return new FitnessResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getLocation(),
                entity.getScheduleText(),
                entity.getPriceLabel(),
                entity.getTag(),
                entity.getImageUrl(),
                entity.getImageUrls(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getTrainerName(),
                entity.getDifficultyLevel(),
                entity.getStatus().name());
    }
}
