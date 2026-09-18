package com.Java.NearBuzz.modules.friendsnearby.controller;

import com.Java.NearBuzz.common.response.ApiResponse;
import com.Java.NearBuzz.modules.friendsnearby.dto.request.FriendMeetupRequest;
import com.Java.NearBuzz.modules.friendsnearby.dto.response.FriendMeetupResponse;
import com.Java.NearBuzz.modules.friendsnearby.service.FriendMeetupService;
import com.Java.NearBuzz.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/friends-nearby")
public class AdminFriendMeetupController {

    private final FriendMeetupService service;
    private final UserRepository userRepository;

    public AdminFriendMeetupController(FriendMeetupService service, UserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ApiResponse<List<FriendMeetupResponse>> list() {
        return ApiResponse.ok("Meetups fetched", service.listAll());
    }

    @PostMapping
    public ApiResponse<FriendMeetupResponse> create(
            @Valid @RequestBody FriendMeetupRequest request, @AuthenticationPrincipal UserDetails principal) {
        Long userId = userRepository.findByEmail(principal.getUsername()).map(u -> u.getId()).orElse(null);
        return ApiResponse.ok("Created", service.create(request, userId));
    }

    @PutMapping("/{id}")
    public ApiResponse<FriendMeetupResponse> update(
            @PathVariable Long id, @Valid @RequestBody FriendMeetupRequest request) {
        return ApiResponse.ok("Updated", service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("Deleted", null);
    }
}
