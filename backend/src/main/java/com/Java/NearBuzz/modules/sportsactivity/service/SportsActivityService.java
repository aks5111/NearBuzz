package com.Java.NearBuzz.modules.sportsactivity.service;

import com.Java.NearBuzz.modules.sportsactivity.dto.request.SportsActivityRequest;
import com.Java.NearBuzz.modules.sportsactivity.dto.response.SportsActivityResponse;

import java.util.List;

public interface SportsActivityService {
    List<SportsActivityResponse> listPublished(String search);

    List<SportsActivityResponse> listAll();

    SportsActivityResponse create(SportsActivityRequest request, Long createdBy);

    SportsActivityResponse update(Long id, SportsActivityRequest request);

    void delete(Long id);
}
