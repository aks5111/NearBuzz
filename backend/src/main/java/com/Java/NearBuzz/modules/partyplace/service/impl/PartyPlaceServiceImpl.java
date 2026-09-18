package com.Java.NearBuzz.modules.partyplace.service.impl;

import com.Java.NearBuzz.common.entity.ListingStatus;
import com.Java.NearBuzz.common.exception.BadRequestException;
import com.Java.NearBuzz.modules.partyplace.dto.request.PartyPlaceRequest;
import com.Java.NearBuzz.modules.partyplace.dto.response.PartyPlaceResponse;
import com.Java.NearBuzz.modules.partyplace.entity.PartyPlace;
import com.Java.NearBuzz.modules.partyplace.repository.PartyPlaceRepository;
import com.Java.NearBuzz.modules.partyplace.service.PartyPlaceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PartyPlaceServiceImpl implements PartyPlaceService {

    private final PartyPlaceRepository repository;

    public PartyPlaceServiceImpl(PartyPlaceRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PartyPlaceResponse> listPublished(String search) {
        String query = (search == null || search.isBlank()) ? null : search.trim().toLowerCase();
        return repository.findByStatusOrderByCreatedAtDesc(ListingStatus.PUBLISHED).stream()
                .filter(e -> query == null || e.getTitle().toLowerCase().contains(query))
                .map(PartyPlaceResponse::from)
                .toList();
    }

    @Override
    public List<PartyPlaceResponse> listAll() {
        return repository.findAll().stream().map(PartyPlaceResponse::from).toList();
    }

    @Override
    @Transactional
    public PartyPlaceResponse create(PartyPlaceRequest request, Long createdBy) {
        PartyPlace entity = new PartyPlace();
        applyRequest(entity, request);
        entity.setCreatedBy(createdBy);
        return PartyPlaceResponse.from(repository.save(entity));
    }

    @Override
    @Transactional
    public PartyPlaceResponse update(Long id, PartyPlaceRequest request) {
        PartyPlace entity = repository.findById(id).orElseThrow(() -> new BadRequestException("Not found"));
        applyRequest(entity, request);
        return PartyPlaceResponse.from(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new BadRequestException("Not found");
        }
        repository.deleteById(id);
    }

    private void applyRequest(PartyPlace entity, PartyPlaceRequest request) {
        entity.setTitle(request.title());
        entity.setDescription(request.description());
        entity.setLocation(request.location());
        entity.setScheduleText(request.scheduleText());
        entity.setPriceLabel(request.priceLabel());
        entity.setTag(request.tag());
        entity.setImageUrl(request.imageUrl());
        entity.setLatitude(request.latitude());
        entity.setLongitude(request.longitude());
        entity.setCapacity(request.capacity());
        if (request.status() != null) {
            try {
                entity.setStatus(ListingStatus.valueOf(request.status()));
            } catch (IllegalArgumentException ex) {
                throw new BadRequestException("Invalid status: " + request.status());
            }
        }
    }
}
