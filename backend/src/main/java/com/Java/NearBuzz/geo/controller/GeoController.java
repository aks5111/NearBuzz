package com.Java.NearBuzz.geo.controller;

import com.Java.NearBuzz.common.exception.BadRequestException;
import com.Java.NearBuzz.common.response.ApiResponse;
import com.Java.NearBuzz.geo.dto.DirectionsResult;
import com.Java.NearBuzz.geo.dto.PlaceResult;
import com.Java.NearBuzz.geo.service.DirectionsService;
import com.Java.NearBuzz.geo.service.GeoRateLimiter;
import com.Java.NearBuzz.geo.service.PlacesService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/geo")
public class GeoController {

    private final PlacesService placesService;
    private final DirectionsService directionsService;
    private final GeoRateLimiter rateLimiter;

    public GeoController(PlacesService placesService, DirectionsService directionsService, GeoRateLimiter rateLimiter) {
        this.placesService = placesService;
        this.directionsService = directionsService;
        this.rateLimiter = rateLimiter;
    }

    @GetMapping("/places/search")
    public ApiResponse<Map<String, Object>> searchPlaces(@RequestParam String q, HttpServletRequest request) {
        checkRateLimit(request);
        List<PlaceResult> results = placesService.search(q);
        return ApiResponse.ok("Places fetched", Map.of("configured", placesService.isConfigured(), "results", results));
    }

    @GetMapping("/directions")
    public ApiResponse<DirectionsResult> directions(
            @RequestParam double originLat,
            @RequestParam double originLng,
            @RequestParam double destLat,
            @RequestParam double destLng,
            HttpServletRequest request) {
        checkRateLimit(request);
        return ApiResponse.ok("Directions fetched", directionsService.getDirections(originLat, originLng, destLat, destLng));
    }

    private void checkRateLimit(HttpServletRequest request) {
        String forwardedFor = request.getHeader("X-Forwarded-For");
        String key = forwardedFor != null ? forwardedFor.split(",")[0].trim() : request.getRemoteAddr();
        if (!rateLimiter.tryConsume(key)) {
            throw new BadRequestException("Too many requests. Please wait a bit.");
        }
    }
}
