package com.Java.NearBuzz.modules.friendsnearby.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record FriendMeetupRequest(
        @NotBlank String title,
        String description,
        @NotBlank String location,
        String scheduleText,
        String priceLabel,
        String tag,
        List<String> imageUrls,
        Double latitude,
        Double longitude,
        Integer minAge,
        Integer maxParticipants,
        String venueName,
        String hostName,
        String hostPhone,
        String hostEmail,
        String hostPhotoUrl,
        String status) {
}
