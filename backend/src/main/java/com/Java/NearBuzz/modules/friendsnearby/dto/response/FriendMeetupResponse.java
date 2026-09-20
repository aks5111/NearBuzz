package com.Java.NearBuzz.modules.friendsnearby.dto.response;

import com.Java.NearBuzz.modules.friendsnearby.entity.FriendMeetup;

import java.util.List;

public record FriendMeetupResponse(
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
        Integer minAge,
        Integer maxParticipants,
        String venueName,
        String hostName,
        String hostPhone,
        String hostEmail,
        String hostPhotoUrl,
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
                entity.getImageUrls(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getMinAge(),
                entity.getMaxParticipants(),
                entity.getVenueName(),
                entity.getHostName(),
                entity.getHostPhone(),
                entity.getHostEmail(),
                entity.getHostPhotoUrl(),
                entity.getStatus().name());
    }
}
