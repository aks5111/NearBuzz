package com.Java.NearBuzz.modules.calisthenics.service;

import com.Java.NearBuzz.modules.calisthenics.dto.request.CalisthenicsRequest;
import com.Java.NearBuzz.modules.calisthenics.dto.response.CalisthenicsResponse;

import java.util.List;

public interface CalisthenicsService {
    List<CalisthenicsResponse> listPublished(String search);

    List<CalisthenicsResponse> listAll();

    CalisthenicsResponse create(CalisthenicsRequest request, Long createdBy);

    CalisthenicsResponse update(Long id, CalisthenicsRequest request);

    void delete(Long id);
}
