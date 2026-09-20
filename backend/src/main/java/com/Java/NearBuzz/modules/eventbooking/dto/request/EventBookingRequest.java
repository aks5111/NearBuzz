package com.Java.NearBuzz.modules.eventbooking.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record EventBookingRequest(
        @NotBlank String title,
        String description,
        @NotBlank String location,
        String scheduleText,
        String priceLabel,
        String tag,
        List<String> imageUrls,
        Double latitude,
        Double longitude,
        String organizerName,
        Integer totalSeats,
        Integer seatsBooked,
        String status) {
}
