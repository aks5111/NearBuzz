package com.Java.NearBuzz.modules.eventbooking.controller;

import com.Java.NearBuzz.common.response.ApiResponse;
import com.Java.NearBuzz.modules.eventbooking.dto.response.EventBookingResponse;
import com.Java.NearBuzz.modules.eventbooking.service.EventBookingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/event-booking")
public class EventBookingController {

    private final EventBookingService service;

    public EventBookingController(EventBookingService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<EventBookingResponse>> list(@RequestParam(required = false) String search) {
        return ApiResponse.ok("Events fetched", service.listPublished(search));
    }
}
