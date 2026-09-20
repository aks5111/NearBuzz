package com.Java.NearBuzz.modules.sportsactivity.dto.response;

import com.Java.NearBuzz.modules.sportsactivity.entity.SportsActivity;

import java.util.List;

public record SportsActivityResponse(
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
        String sportType,
        Integer teamSize,
        String venueName,
        String organizerName,
        String organizerPhone,
        String organizerEmail,
        String organizerPhotoUrl,
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
                entity.getImageUrls(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getSportType(),
                entity.getTeamSize(),
                entity.getVenueName(),
                entity.getOrganizerName(),
                entity.getOrganizerPhone(),
                entity.getOrganizerEmail(),
                entity.getOrganizerPhotoUrl(),
                entity.getStatus().name());
    }
}
