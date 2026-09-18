package com.Java.NearBuzz.modules.sportsactivity.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SportsActivityRequest(
        @NotBlank String title,
        String description,
        @NotBlank String location,
        String scheduleText,
        String priceLabel,
        String tag,
        String imageUrl,
        Double latitude,
        Double longitude,
        String sportType,
        Integer teamSize,
        String status) {
}
