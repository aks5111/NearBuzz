package com.Java.NearBuzz.geo.service;

import com.Java.NearBuzz.geo.dto.DirectionsResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
public class DirectionsService {

    private static final Logger log = LoggerFactory.getLogger(DirectionsService.class);

    private final RestClient restClient;
    private final String apiKey;

    public DirectionsService(@Value("${geo.google-api-key:}") String apiKey) {
        this.apiKey = apiKey;
        this.restClient = RestClient.builder().baseUrl("https://maps.googleapis.com/maps/api").build();
    }

    public DirectionsResult getDirections(double originLat, double originLng, double destLat, double destLng) {
        String deepLink = "https://www.google.com/maps/dir/?api=1&origin=%s,%s&destination=%s,%s"
                .formatted(originLat, originLng, destLat, destLng);

        if (apiKey == null || apiKey.isBlank()) {
            return new DirectionsResult(false, deepLink, null, List.of());
        }

        try {
            Map<?, ?> response = restClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/directions/json")
                            .queryParam("origin", originLat + "," + originLng)
                            .queryParam("destination", destLat + "," + destLng)
                            .queryParam("key", apiKey)
                            .build())
                    .retrieve()
                    .body(Map.class);

            List<?> routes = (List<?>) response.get("routes");
            if (routes == null || routes.isEmpty()) {
                return new DirectionsResult(false, deepLink, null, List.of());
            }

            Map<?, ?> route = (Map<?, ?>) routes.get(0);
            Map<?, ?> polyline = (Map<?, ?>) route.get("overview_polyline");
            String encoded = polyline != null ? (String) polyline.get("points") : null;

            return new DirectionsResult(true, deepLink, encoded, List.of());
        } catch (Exception ex) {
            log.error("Directions request failed", ex);
            return new DirectionsResult(false, deepLink, null, List.of());
        }
    }
}
