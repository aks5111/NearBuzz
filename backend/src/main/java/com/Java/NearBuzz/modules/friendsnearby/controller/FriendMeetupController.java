package com.Java.NearBuzz.modules.friendsnearby.controller;

import com.Java.NearBuzz.common.response.ApiResponse;
import com.Java.NearBuzz.modules.friendsnearby.dto.response.FriendMeetupResponse;
import com.Java.NearBuzz.modules.friendsnearby.service.FriendMeetupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/friends-nearby")
public class FriendMeetupController {

    private final FriendMeetupService service;

    public FriendMeetupController(FriendMeetupService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<FriendMeetupResponse>> list(@RequestParam(required = false) String search) {
        return ApiResponse.ok("Meetups fetched", service.listPublished(search));
    }
}
