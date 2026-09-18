package com.Java.NearBuzz.modules.travel.service;

import com.Java.NearBuzz.modules.travel.dto.request.TravelRequest;
import com.Java.NearBuzz.modules.travel.dto.response.TravelResponse;

import java.util.List;

public interface TravelService {
    List<TravelResponse> listPublished(String search);

    List<TravelResponse> listAll();

    TravelResponse create(TravelRequest request, Long createdBy);

    TravelResponse update(Long id, TravelRequest request);

    void delete(Long id);
}
