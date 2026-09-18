package com.Java.NearBuzz.modules.partyplace.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PartyPlaceRequest(
        @NotBlank String title,
        String description,
        @NotBlank String location,
        String scheduleText,
        String priceLabel,
        String tag,
        String imageUrl,
        Double latitude,
        Double longitude,
        Integer capacity,
        String status) {
}
