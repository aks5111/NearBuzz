package com.Java.NearBuzz.modules.friendsnearby.dto.response;

import com.Java.NearBuzz.modules.friendsnearby.entity.FriendMeetup;

public record FriendMeetupResponse(
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
        Integer minAge,
        Integer maxParticipants,
        String status) {

    public static FriendMeetupResponse from(FriendMeetup entity) {
        return new FriendMeetupResponse(
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
                entity.getMinAge(),
                entity.getMaxParticipants(),
                entity.getStatus().name());
    }
}
