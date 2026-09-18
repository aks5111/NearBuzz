package com.Java.NearBuzz.modules.partyplace.controller;

import com.Java.NearBuzz.common.response.ApiResponse;
import com.Java.NearBuzz.modules.partyplace.dto.request.PartyPlaceRequest;
import com.Java.NearBuzz.modules.partyplace.dto.response.PartyPlaceResponse;
import com.Java.NearBuzz.modules.partyplace.service.PartyPlaceService;
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
@RequestMapping("/api/v1/admin/party-place")
public class AdminPartyPlaceController {

    private final PartyPlaceService service;
    private final UserRepository userRepository;

    public AdminPartyPlaceController(PartyPlaceService service, UserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ApiResponse<List<PartyPlaceResponse>> list() {
        return ApiResponse.ok("Party place listings fetched", service.listAll());
    }

    @PostMapping
    public ApiResponse<PartyPlaceResponse> create(
            @Valid @RequestBody PartyPlaceRequest request, @AuthenticationPrincipal UserDetails principal) {
        Long userId = userRepository.findByEmail(principal.getUsername()).map(u -> u.getId()).orElse(null);
        return ApiResponse.ok("Created", service.create(request, userId));
    }

    @PutMapping("/{id}")
    public ApiResponse<PartyPlaceResponse> update(
            @PathVariable Long id, @Valid @RequestBody PartyPlaceRequest request) {
        return ApiResponse.ok("Updated", service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("Deleted", null);
    }
}
