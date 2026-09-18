package com.Java.NearBuzz.modules.friendsnearby.service;

import com.Java.NearBuzz.modules.friendsnearby.dto.request.FriendMeetupRequest;
import com.Java.NearBuzz.modules.friendsnearby.dto.response.FriendMeetupResponse;

import java.util.List;

public interface FriendMeetupService {
    List<FriendMeetupResponse> listPublished(String search);

    List<FriendMeetupResponse> listAll();

    FriendMeetupResponse create(FriendMeetupRequest request, Long createdBy);

    FriendMeetupResponse update(Long id, FriendMeetupRequest request);

    void delete(Long id);
}
