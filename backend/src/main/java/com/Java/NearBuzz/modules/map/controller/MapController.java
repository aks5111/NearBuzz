package com.Java.NearBuzz.modules.map.controller;

import com.Java.NearBuzz.common.response.ApiResponse;
import com.Java.NearBuzz.modules.map.dto.MapPinResponse;
import com.Java.NearBuzz.modules.map.service.MapPinService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/map")
public class MapController {

    private final MapPinService mapPinService;

    public MapController(MapPinService mapPinService) {
        this.mapPinService = mapPinService;
    }

    @GetMapping("/pins")
    public ApiResponse<List<MapPinResponse>> pins() {
        return ApiResponse.ok("Pins fetched", mapPinService.findAllPins());
    }
}
