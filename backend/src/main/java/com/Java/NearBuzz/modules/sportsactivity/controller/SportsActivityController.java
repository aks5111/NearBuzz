package com.Java.NearBuzz.modules.sportsactivity.controller;

import com.Java.NearBuzz.common.response.ApiResponse;
import com.Java.NearBuzz.modules.sportsactivity.dto.response.SportsActivityResponse;
import com.Java.NearBuzz.modules.sportsactivity.service.SportsActivityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sports-activity")
public class SportsActivityController {

    private final SportsActivityService service;

    public SportsActivityController(SportsActivityService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<SportsActivityResponse>> list(@RequestParam(required = false) String search) {
        return ApiResponse.ok("Sports activities fetched", service.listPublished(search));
    }
}
