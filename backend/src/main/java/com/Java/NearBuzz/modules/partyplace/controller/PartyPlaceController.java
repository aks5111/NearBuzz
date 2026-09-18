package com.Java.NearBuzz.modules.partyplace.controller;

import com.Java.NearBuzz.common.response.ApiResponse;
import com.Java.NearBuzz.modules.partyplace.dto.response.PartyPlaceResponse;
import com.Java.NearBuzz.modules.partyplace.service.PartyPlaceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/party-place")
public class PartyPlaceController {

    private final PartyPlaceService service;

    public PartyPlaceController(PartyPlaceService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<PartyPlaceResponse>> list(@RequestParam(required = false) String search) {
        return ApiResponse.ok("Party place listings fetched", service.listPublished(search));
    }
}
