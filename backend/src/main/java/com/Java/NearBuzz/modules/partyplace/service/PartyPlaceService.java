package com.Java.NearBuzz.modules.partyplace.service;

import com.Java.NearBuzz.modules.partyplace.dto.request.PartyPlaceRequest;
import com.Java.NearBuzz.modules.partyplace.dto.response.PartyPlaceResponse;

import java.util.List;

public interface PartyPlaceService {
    List<PartyPlaceResponse> listPublished(String search);

    List<PartyPlaceResponse> listAll();

    PartyPlaceResponse create(PartyPlaceRequest request, Long createdBy);

    PartyPlaceResponse update(Long id, PartyPlaceRequest request);

    void delete(Long id);
}
