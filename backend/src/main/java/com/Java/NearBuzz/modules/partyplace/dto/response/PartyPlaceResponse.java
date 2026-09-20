package com.Java.NearBuzz.modules.partyplace.dto.response;

import com.Java.NearBuzz.modules.partyplace.entity.PartyPlace;

import java.util.List;

public record PartyPlaceResponse(
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
        Integer capacity,
        String status) {

    public static PartyPlaceResponse from(PartyPlace entity) {
        return new PartyPlaceResponse(
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
                entity.getCapacity(),
                entity.getStatus().name());
    }
}
