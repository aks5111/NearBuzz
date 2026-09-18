package com.Java.NearBuzz.modules.eventbooking.dto.request;

import jakarta.validation.constraints.NotBlank;

public record EventBookingRequest(
        @NotBlank String title,
        String description,
        @NotBlank String location,
        String scheduleText,
        String priceLabel,
        String tag,
        String imageUrl,
        Double latitude,
        Double longitude,
        String organizerName,
        Integer totalSeats,
        Integer seatsBooked,
        String status) {
}
