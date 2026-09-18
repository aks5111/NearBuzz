package com.Java.NearBuzz.modules.friendsnearby.service.impl;

import com.Java.NearBuzz.common.entity.ListingStatus;
import com.Java.NearBuzz.common.exception.BadRequestException;
import com.Java.NearBuzz.modules.friendsnearby.dto.request.FriendMeetupRequest;
import com.Java.NearBuzz.modules.friendsnearby.dto.response.FriendMeetupResponse;
import com.Java.NearBuzz.modules.friendsnearby.entity.FriendMeetup;
import com.Java.NearBuzz.modules.friendsnearby.repository.FriendMeetupRepository;
import com.Java.NearBuzz.modules.friendsnearby.service.FriendMeetupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FriendMeetupServiceImpl implements FriendMeetupService {

    private final FriendMeetupRepository repository;

    public FriendMeetupServiceImpl(FriendMeetupRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FriendMeetupResponse> listPublished(String search) {
        String query = (search == null || search.isBlank()) ? null : search.trim().toLowerCase();
        return repository.findByStatusOrderByCreatedAtDesc(ListingStatus.PUBLISHED).stream()
                .filter(e -> query == null || e.getTitle().toLowerCase().contains(query))
                .map(FriendMeetupResponse::from)
                .toList();
    }

    @Override
    public List<FriendMeetupResponse> listAll() {
        return repository.findAll().stream().map(FriendMeetupResponse::from).toList();
    }

    @Override
    @Transactional
    public FriendMeetupResponse create(FriendMeetupRequest request, Long createdBy) {
        FriendMeetup entity = new FriendMeetup();
        applyRequest(entity, request);
        entity.setCreatedBy(createdBy);
        return FriendMeetupResponse.from(repository.save(entity));
    }

    @Override
    @Transactional
    public FriendMeetupResponse update(Long id, FriendMeetupRequest request) {
        FriendMeetup entity = repository.findById(id).orElseThrow(() -> new BadRequestException("Not found"));
        applyRequest(entity, request);
        return FriendMeetupResponse.from(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new BadRequestException("Not found");
        }
        repository.deleteById(id);
    }

    private void applyRequest(FriendMeetup entity, FriendMeetupRequest request) {
        entity.setTitle(request.title());
        entity.setDescription(request.description());
        entity.setLocation(request.location());
        entity.setScheduleText(request.scheduleText());
        entity.setPriceLabel(request.priceLabel());
        entity.setTag(request.tag());
        entity.setImageUrl(request.imageUrl());
        entity.setLatitude(request.latitude());
        entity.setLongitude(request.longitude());
        entity.setMinAge(request.minAge());
        entity.setMaxParticipants(request.maxParticipants());
        if (request.status() != null) {
            try {
                entity.setStatus(ListingStatus.valueOf(request.status()));
            } catch (IllegalArgumentException ex) {
                throw new BadRequestException("Invalid status: " + request.status());
            }
        }
    }
}
