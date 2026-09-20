package com.Java.NearBuzz.modules.travel.service.impl;

import com.Java.NearBuzz.common.entity.ListingStatus;
import com.Java.NearBuzz.common.exception.BadRequestException;
import com.Java.NearBuzz.modules.travel.dto.request.TravelRequest;
import com.Java.NearBuzz.modules.travel.dto.response.TravelResponse;
import com.Java.NearBuzz.modules.travel.entity.Travel;
import com.Java.NearBuzz.modules.travel.repository.TravelRepository;
import com.Java.NearBuzz.modules.travel.service.TravelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TravelServiceImpl implements TravelService {

    private final TravelRepository repository;

    public TravelServiceImpl(TravelRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TravelResponse> listPublished(String search) {
        String query = (search == null || search.isBlank()) ? null : search.trim().toLowerCase();
        return repository.findByStatusOrderByCreatedAtDesc(ListingStatus.PUBLISHED).stream()
                .filter(e -> query == null
                        || e.getTitle().toLowerCase().contains(query)
                        || e.getLocation().toLowerCase().contains(query))
                .map(TravelResponse::from)
                .toList();
    }

    @Override
    public List<TravelResponse> listAll() {
        return repository.findAll().stream().map(TravelResponse::from).toList();
    }

    @Override
    @Transactional
    public TravelResponse create(TravelRequest request, Long createdBy) {
        Travel entity = new Travel();
        applyRequest(entity, request);
        entity.setCreatedBy(createdBy);
        return TravelResponse.from(repository.save(entity));
    }

    @Override
    @Transactional
    public TravelResponse update(Long id, TravelRequest request) {
        Travel entity = repository.findById(id).orElseThrow(() -> new BadRequestException("Not found"));
        applyRequest(entity, request);
        return TravelResponse.from(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new BadRequestException("Not found");
        }
        repository.deleteById(id);
    }

    private void applyRequest(Travel entity, TravelRequest request) {
        entity.setTitle(request.title());
        entity.setDescription(request.description());
        entity.setLocation(request.location());
        entity.setScheduleText(request.scheduleText());
        entity.setPriceLabel(request.priceLabel());
        entity.setTag(request.tag());
        entity.setImages(request.imageUrls());
        entity.setLatitude(request.latitude());
        entity.setLongitude(request.longitude());
        entity.setDurationDays(request.durationDays());
        entity.setGroupSize(request.groupSize());
        entity.setInclusions(request.inclusions());
        entity.setAgencyName(request.agencyName());
        entity.setAgencyContactName(request.agencyContactName());
        entity.setAgencyPhone(request.agencyPhone());
        entity.setAgencyEmail(request.agencyEmail());
        entity.setAgencyPhotoUrl(request.agencyPhotoUrl());
        if (request.status() != null) {
            try {
                entity.setStatus(ListingStatus.valueOf(request.status()));
            } catch (IllegalArgumentException ex) {
                throw new BadRequestException("Invalid status: " + request.status());
            }
        }
    }
}
