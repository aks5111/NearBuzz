package com.Java.NearBuzz.modules.fitness.service.impl;

import com.Java.NearBuzz.common.entity.ListingStatus;
import com.Java.NearBuzz.common.exception.BadRequestException;
import com.Java.NearBuzz.modules.fitness.dto.request.FitnessRequest;
import com.Java.NearBuzz.modules.fitness.dto.response.FitnessResponse;
import com.Java.NearBuzz.modules.fitness.entity.Fitness;
import com.Java.NearBuzz.modules.fitness.repository.FitnessRepository;
import com.Java.NearBuzz.modules.fitness.service.FitnessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FitnessServiceImpl implements FitnessService {

    private final FitnessRepository repository;

    public FitnessServiceImpl(FitnessRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FitnessResponse> listPublished(String search) {
        String query = (search == null || search.isBlank()) ? null : search.trim().toLowerCase();
        return repository.findByStatusOrderByCreatedAtDesc(ListingStatus.PUBLISHED).stream()
                .filter(e -> query == null || e.getTitle().toLowerCase().contains(query))
                .map(FitnessResponse::from)
                .toList();
    }

    @Override
    public List<FitnessResponse> listAll() {
        return repository.findAll().stream().map(FitnessResponse::from).toList();
    }

    @Override
    @Transactional
    public FitnessResponse create(FitnessRequest request, Long createdBy) {
        Fitness entity = new Fitness();
        applyRequest(entity, request);
        entity.setCreatedBy(createdBy);
        return FitnessResponse.from(repository.save(entity));
    }

    @Override
    @Transactional
    public FitnessResponse update(Long id, FitnessRequest request) {
        Fitness entity = repository.findById(id).orElseThrow(() -> new BadRequestException("Not found"));
        applyRequest(entity, request);
        return FitnessResponse.from(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new BadRequestException("Not found");
        }
        repository.deleteById(id);
    }

    private void applyRequest(Fitness entity, FitnessRequest request) {
        entity.setTitle(request.title());
        entity.setDescription(request.description());
        entity.setLocation(request.location());
        entity.setScheduleText(request.scheduleText());
        entity.setPriceLabel(request.priceLabel());
        entity.setTag(request.tag());
        entity.setImages(request.imageUrls());
        entity.setLatitude(request.latitude());
        entity.setLongitude(request.longitude());
        entity.setTrainerName(request.trainerName());
        entity.setDifficultyLevel(request.difficultyLevel());
        if (request.status() != null) {
            try {
                entity.setStatus(ListingStatus.valueOf(request.status()));
            } catch (IllegalArgumentException ex) {
                throw new BadRequestException("Invalid status: " + request.status());
            }
        }
    }
}
