package com.Java.NearBuzz.modules.travel.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TravelRequest(
        @NotBlank String title,
        String description,
        @NotBlank String location,
        String scheduleText,
        String priceLabel,
        String tag,
        String imageUrl,
        Double latitude,
        Double longitude,
        Integer durationDays,
        Integer groupSize,
        String status) {
}
