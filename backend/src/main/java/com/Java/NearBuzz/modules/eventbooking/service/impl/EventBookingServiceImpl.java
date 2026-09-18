package com.Java.NearBuzz.modules.eventbooking.service.impl;

import com.Java.NearBuzz.common.entity.ListingStatus;
import com.Java.NearBuzz.common.exception.BadRequestException;
import com.Java.NearBuzz.modules.eventbooking.dto.request.EventBookingRequest;
import com.Java.NearBuzz.modules.eventbooking.dto.response.EventBookingResponse;
import com.Java.NearBuzz.modules.eventbooking.entity.EventBooking;
import com.Java.NearBuzz.modules.eventbooking.repository.EventBookingRepository;
import com.Java.NearBuzz.modules.eventbooking.service.EventBookingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventBookingServiceImpl implements EventBookingService {

    private final EventBookingRepository repository;

    public EventBookingServiceImpl(EventBookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EventBookingResponse> listPublished(String search) {
        String query = (search == null || search.isBlank()) ? null : search.trim().toLowerCase();
        return repository.findByStatusOrderByCreatedAtDesc(ListingStatus.PUBLISHED).stream()
                .filter(e -> query == null || e.getTitle().toLowerCase().contains(query))
                .map(EventBookingResponse::from)
                .toList();
    }

    @Override
    public List<EventBookingResponse> listAll() {
        return repository.findAll().stream().map(EventBookingResponse::from).toList();
    }

    @Override
    @Transactional
    public EventBookingResponse create(EventBookingRequest request, Long createdBy) {
        EventBooking entity = new EventBooking();
        applyRequest(entity, request);
        entity.setCreatedBy(createdBy);
        return EventBookingResponse.from(repository.save(entity));
    }

    @Override
    @Transactional
    public EventBookingResponse update(Long id, EventBookingRequest request) {
        EventBooking entity = repository.findById(id).orElseThrow(() -> new BadRequestException("Not found"));
        applyRequest(entity, request);
        return EventBookingResponse.from(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new BadRequestException("Not found");
        }
        repository.deleteById(id);
    }

    private void applyRequest(EventBooking entity, EventBookingRequest request) {
        entity.setTitle(request.title());
        entity.setDescription(request.description());
        entity.setLocation(request.location());
        entity.setScheduleText(request.scheduleText());
        entity.setPriceLabel(request.priceLabel());
        entity.setTag(request.tag());
        entity.setImageUrl(request.imageUrl());
        entity.setLatitude(request.latitude());
        entity.setLongitude(request.longitude());
        entity.setOrganizerName(request.organizerName());
        entity.setTotalSeats(request.totalSeats());
        entity.setSeatsBooked(request.seatsBooked() != null ? request.seatsBooked() : 0);
        if (request.status() != null) {
            try {
                entity.setStatus(ListingStatus.valueOf(request.status()));
            } catch (IllegalArgumentException ex) {
                throw new BadRequestException("Invalid status: " + request.status());
            }
        }
    }
}
