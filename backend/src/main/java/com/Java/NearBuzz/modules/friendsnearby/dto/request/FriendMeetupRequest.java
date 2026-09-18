package com.Java.NearBuzz.modules.friendsnearby.dto.request;

import jakarta.validation.constraints.NotBlank;

public record FriendMeetupRequest(
        @NotBlank String title,
        String description,
        @NotBlank String location,
        String scheduleText,
        String priceLabel,
        String tag,
        String imageUrl,
        Double latitude,
        Double longitude,
        Integer minAge,
        Integer maxParticipants,
        String status) {
}
