package com.Java.NearBuzz.modules.eventbooking.service;

import com.Java.NearBuzz.modules.eventbooking.dto.request.EventBookingRequest;
import com.Java.NearBuzz.modules.eventbooking.dto.response.EventBookingResponse;

import java.util.List;

public interface EventBookingService {
    List<EventBookingResponse> listPublished(String search);

    List<EventBookingResponse> listAll();

    EventBookingResponse create(EventBookingRequest request, Long createdBy);

    EventBookingResponse update(Long id, EventBookingRequest request);

    void delete(Long id);
}
