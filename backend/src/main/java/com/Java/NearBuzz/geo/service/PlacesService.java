package com.Java.NearBuzz.geo.service;

import com.Java.NearBuzz.geo.dto.PlaceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PlacesService {

    private static final Logger log = LoggerFactory.getLogger(PlacesService.class);

    private final RestClient restClient;
    private final String apiKey;

    public PlacesService(@Value("${geo.google-api-key:}") String apiKey) {
        this.apiKey = apiKey;
        this.restClient = RestClient.builder().baseUrl("https://maps.googleapis.com/maps/api").build();
    }

    public boolean isConfigured() {
        return apiKey != null && !apiKey.isBlank();
    }

    public List<PlaceResult> search(String query) {
        if (!isConfigured()) {
            return List.of();
        }

        try {
            Map<?, ?> response = restClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/place/textsearch/json")
                            .queryParam("query", query)
                            .queryParam("key", apiKey)
                            .build())
                    .retrieve()
                    .body(Map.class);

            List<?> results = (List<?>) response.get("results");
            if (results == null) return List.of();

            List<PlaceResult> places = new ArrayList<>();
            for (Object item : results) {
                Map<?, ?> place = (Map<?, ?>) item;
                Map<?, ?> geometry = (Map<?, ?>) place.get("geometry");
                Map<?, ?> location = (Map<?, ?>) geometry.get("location");
                places.add(new PlaceResult(
                        (String) place.get("name"),
                        (String) place.get("formatted_address"),
                        ((Number) location.get("lat")).doubleValue(),
                        ((Number) location.get("lng")).doubleValue()));
            }
            return places;
        } catch (Exception ex) {
            log.error("Places search failed for '{}'", query, ex);
            return List.of();
        }
    }
}
