package com.Java.NearBuzz.modules.travel.controller;

import com.Java.NearBuzz.common.response.ApiResponse;
import com.Java.NearBuzz.modules.travel.dto.response.TravelResponse;
import com.Java.NearBuzz.modules.travel.service.TravelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/travel")
public class TravelController {

    private final TravelService service;

    public TravelController(TravelService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<TravelResponse>> list(@RequestParam(required = false) String search) {
        return ApiResponse.ok("Travel listings fetched", service.listPublished(search));
    }
}
