package com.Java.NearBuzz.modules.eventbooking.dto.response;

import com.Java.NearBuzz.modules.eventbooking.entity.EventBooking;

public record EventBookingResponse(
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
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getOrganizerName(),
                entity.getTotalSeats(),
                entity.getSeatsBooked(),
                entity.getStatus().name());
    }
}
