package com.Java.NearBuzz.modules.fitness.service;

import com.Java.NearBuzz.modules.fitness.dto.request.FitnessRequest;
import com.Java.NearBuzz.modules.fitness.dto.response.FitnessResponse;

import java.util.List;

public interface FitnessService {
    List<FitnessResponse> listPublished(String search);

    List<FitnessResponse> listAll();

    FitnessResponse create(FitnessRequest request, Long createdBy);

    FitnessResponse update(Long id, FitnessRequest request);

    void delete(Long id);
}
