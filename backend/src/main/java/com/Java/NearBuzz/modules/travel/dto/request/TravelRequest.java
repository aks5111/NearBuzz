package com.Java.NearBuzz.modules.travel.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record TravelRequest(
        @NotBlank String title,
        String description,
        @NotBlank String location,
        String scheduleText,
        String priceLabel,
        String tag,
        List<String> imageUrls,
        Double latitude,
        Double longitude,
        Integer durationDays,
        Integer groupSize,
        String inclusions,
        String agencyName,
        String agencyContactName,
        String agencyPhone,
        String agencyEmail,
        String agencyPhotoUrl,
        String status) {
}
