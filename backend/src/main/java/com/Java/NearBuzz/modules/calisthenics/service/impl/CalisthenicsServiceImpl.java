package com.Java.NearBuzz.modules.calisthenics.service.impl;

import com.Java.NearBuzz.common.entity.ListingStatus;
import com.Java.NearBuzz.common.exception.BadRequestException;
import com.Java.NearBuzz.modules.calisthenics.dto.request.CalisthenicsRequest;
import com.Java.NearBuzz.modules.calisthenics.dto.response.CalisthenicsResponse;
import com.Java.NearBuzz.modules.calisthenics.entity.Calisthenics;
import com.Java.NearBuzz.modules.calisthenics.repository.CalisthenicsRepository;
import com.Java.NearBuzz.modules.calisthenics.service.CalisthenicsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CalisthenicsServiceImpl implements CalisthenicsService {

    private final CalisthenicsRepository repository;

    public CalisthenicsServiceImpl(CalisthenicsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CalisthenicsResponse> listPublished(String search) {
        String query = (search == null || search.isBlank()) ? null : search.trim().toLowerCase();
        return repository.findByStatusOrderByCreatedAtDesc(ListingStatus.PUBLISHED).stream()
                .filter(e -> query == null || e.getTitle().toLowerCase().contains(query))
                .map(CalisthenicsResponse::from)
                .toList();
    }

    @Override
    public List<CalisthenicsResponse> listAll() {
        return repository.findAll().stream().map(CalisthenicsResponse::from).toList();
    }

    @Override
    @Transactional
    public CalisthenicsResponse create(CalisthenicsRequest request, Long createdBy) {
        Calisthenics entity = new Calisthenics();
        applyRequest(entity, request);
        entity.setCreatedBy(createdBy);
        return CalisthenicsResponse.from(repository.save(entity));
    }

    @Override
    @Transactional
    public CalisthenicsResponse update(Long id, CalisthenicsRequest request) {
        Calisthenics entity = repository.findById(id).orElseThrow(() -> new BadRequestException("Not found"));
        applyRequest(entity, request);
        return CalisthenicsResponse.from(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new BadRequestException("Not found");
        }
        repository.deleteById(id);
    }

    private void applyRequest(Calisthenics entity, CalisthenicsRequest request) {
        entity.setTitle(request.title());
        entity.setDescription(request.description());
        entity.setLocation(request.location());
        entity.setScheduleText(request.scheduleText());
        entity.setPriceLabel(request.priceLabel());
        entity.setTag(request.tag());
        entity.setImages(request.imageUrls());
        entity.setLatitude(request.latitude());
        entity.setLongitude(request.longitude());
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
