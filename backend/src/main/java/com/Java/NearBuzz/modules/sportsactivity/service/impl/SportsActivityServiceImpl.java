package com.Java.NearBuzz.modules.sportsactivity.service.impl;

import com.Java.NearBuzz.common.entity.ListingStatus;
import com.Java.NearBuzz.common.exception.BadRequestException;
import com.Java.NearBuzz.modules.sportsactivity.dto.request.SportsActivityRequest;
import com.Java.NearBuzz.modules.sportsactivity.dto.response.SportsActivityResponse;
import com.Java.NearBuzz.modules.sportsactivity.entity.SportsActivity;
import com.Java.NearBuzz.modules.sportsactivity.repository.SportsActivityRepository;
import com.Java.NearBuzz.modules.sportsactivity.service.SportsActivityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SportsActivityServiceImpl implements SportsActivityService {

    private final SportsActivityRepository repository;

    public SportsActivityServiceImpl(SportsActivityRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SportsActivityResponse> listPublished(String search) {
        String query = (search == null || search.isBlank()) ? null : search.trim().toLowerCase();
        return repository.findByStatusOrderByCreatedAtDesc(ListingStatus.PUBLISHED).stream()
                .filter(e -> query == null
                        || e.getTitle().toLowerCase().contains(query)
                        || e.getLocation().toLowerCase().contains(query))
                .map(SportsActivityResponse::from)
                .toList();
    }

    @Override
    public List<SportsActivityResponse> listAll() {
        return repository.findAll().stream().map(SportsActivityResponse::from).toList();
    }

    @Override
    @Transactional
    public SportsActivityResponse create(SportsActivityRequest request, Long createdBy) {
        SportsActivity entity = new SportsActivity();
        applyRequest(entity, request);
        entity.setCreatedBy(createdBy);
        return SportsActivityResponse.from(repository.save(entity));
    }

    @Override
    @Transactional
    public SportsActivityResponse update(Long id, SportsActivityRequest request) {
        SportsActivity entity = repository.findById(id).orElseThrow(() -> new BadRequestException("Not found"));
        applyRequest(entity, request);
        return SportsActivityResponse.from(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new BadRequestException("Not found");
        }
        repository.deleteById(id);
    }

    private void applyRequest(SportsActivity entity, SportsActivityRequest request) {
        entity.setTitle(request.title());
        entity.setDescription(request.description());
        entity.setLocation(request.location());
        entity.setScheduleText(request.scheduleText());
        entity.setPriceLabel(request.priceLabel());
        entity.setTag(request.tag());
        entity.setImages(request.imageUrls());
        entity.setLatitude(request.latitude());
        entity.setLongitude(request.longitude());
        entity.setSportType(request.sportType());
        entity.setTeamSize(request.teamSize());
        entity.setVenueName(request.venueName());
        entity.setOrganizerName(request.organizerName());
        entity.setOrganizerPhone(request.organizerPhone());
        entity.setOrganizerEmail(request.organizerEmail());
        entity.setOrganizerPhotoUrl(request.organizerPhotoUrl());
        if (request.status() != null) {
            try {
                entity.setStatus(ListingStatus.valueOf(request.status()));
            } catch (IllegalArgumentException ex) {
                throw new BadRequestException("Invalid status: " + request.status());
            }
        }
    }
}
