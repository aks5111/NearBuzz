package com.Java.NearBuzz.modules.calisthenics.dto.response;

import com.Java.NearBuzz.modules.calisthenics.entity.Calisthenics;

public record CalisthenicsResponse(
        Long id,
        String title,
        String description,
        String location,
        String scheduleText,
        String priceLabel,
        String tag,
        String imageUrl,
        Double latitude,
        Double longitude,
        String difficultyLevel,
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
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getDifficultyLevel(),
                entity.getStatus().name());
    }
}
