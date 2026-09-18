package com.Java.NearBuzz.modules.map.dto;

public record MapPinResponse(
        Long id,
        String module,
        String title,
        String location,
        String scheduleText,
        String priceLabel,
        String imageUrl,
        Double latitude,
        Double longitude) {
}
