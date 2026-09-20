package com.Java.NearBuzz.modules.partyplace.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record PartyPlaceRequest(
        @NotBlank String title,
        String description,
        @NotBlank String location,
        String scheduleText,
        String priceLabel,
        String tag,
        List<String> imageUrls,
        Double latitude,
        Double longitude,
        Integer capacity,
        String status) {
}
