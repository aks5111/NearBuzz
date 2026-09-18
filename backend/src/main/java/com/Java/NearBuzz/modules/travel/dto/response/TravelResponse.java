package com.Java.NearBuzz.modules.travel.dto.response;

import com.Java.NearBuzz.modules.travel.entity.Travel;

public record TravelResponse(
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
        Integer durationDays,
        Integer groupSize,
        String status) {

    public static TravelResponse from(Travel entity) {
        return new TravelResponse(
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
                entity.getDurationDays(),
                entity.getGroupSize(),
                entity.getStatus().name());
    }
}
