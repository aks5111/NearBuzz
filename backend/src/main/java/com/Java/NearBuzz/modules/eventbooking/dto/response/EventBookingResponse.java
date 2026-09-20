package com.Java.NearBuzz.modules.eventbooking.dto.response;

import com.Java.NearBuzz.modules.eventbooking.entity.EventBooking;

import java.util.List;

public record EventBookingResponse(
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
        String organizerName,
        Integer totalSeats,
        Integer seatsBooked,
        String status) {

    public static EventBookingResponse from(EventBooking entity) {
        return new EventBookingResponse(
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
                entity.getOrganizerName(),
                entity.getTotalSeats(),
                entity.getSeatsBooked(),
                entity.getStatus().name());
    }
}
