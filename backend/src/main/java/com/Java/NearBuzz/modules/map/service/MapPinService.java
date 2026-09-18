package com.Java.NearBuzz.modules.map.service;

import com.Java.NearBuzz.modules.map.dto.MapPinResponse;
import com.Java.NearBuzz.modules.map.repository.MapPinRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapPinService {

    private final MapPinRepository mapPinRepository;

    public MapPinService(MapPinRepository mapPinRepository) {
        this.mapPinRepository = mapPinRepository;
    }

    public List<MapPinResponse> findAllPins() {
        return mapPinRepository.findAllPins();
    }
}
