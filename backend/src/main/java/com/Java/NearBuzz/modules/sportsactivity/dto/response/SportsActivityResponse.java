package com.Java.NearBuzz.modules.sportsactivity.dto.response;

import com.Java.NearBuzz.modules.sportsactivity.entity.SportsActivity;

public record SportsActivityResponse(
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
        String sportType,
        Integer teamSize,
        String status) {

    public static SportsActivityResponse from(SportsActivity entity) {
        return new SportsActivityResponse(
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
                entity.getSportType(),
                entity.getTeamSize(),
                entity.getStatus().name());
    }
}
