package com.Java.NearBuzz.modules.calisthenics.dto.response;

import com.Java.NearBuzz.modules.calisthenics.entity.Calisthenics;

import java.util.List;

public record CalisthenicsResponse(
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
        String difficultyLevel,
        String gymName,
        String trainerContactName,
        String trainerPhone,
        String trainerEmail,
        String trainerPhotoUrl,
        String status) {

    public static CalisthenicsResponse from(Calisthenics entity) {
        return new CalisthenicsResponse(
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
                entity.getDifficultyLevel(),
                entity.getGymName(),
                entity.getTrainerContactName(),
                entity.getTrainerPhone(),
                entity.getTrainerEmail(),
                entity.getTrainerPhotoUrl(),
                entity.getStatus().name());
    }
}
