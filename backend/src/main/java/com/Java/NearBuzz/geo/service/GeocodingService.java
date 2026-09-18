package com.Java.NearBuzz.geo.service;

import com.Java.NearBuzz.geo.dto.GeocodeResult;
import com.Java.NearBuzz.geo.entity.GeocodeCache;
import com.Java.NearBuzz.geo.repository.GeocodeCacheRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Each normalized address is looked up via Google Geocoding at most once,
 * ever (geo_geocode_cache). A retryable failure (quota/deny/unknown) is
 * NOT cached so the next attempt retries it; ZERO_RESULTS IS cached, since
 * asking Google again won't change that answer.
 */
@Service
public class GeocodingService {

    private static final Logger log = LoggerFactory.getLogger(GeocodingService.class);
    private static final Set<String> RETRYABLE_STATUSES =
            Set.of("OVER_QUERY_LIMIT", "REQUEST_DENIED", "UNKNOWN_ERROR");

    private final GeocodeCacheRepository cacheRepository;
    private final RestClient restClient;
    private final String apiKey;

    public GeocodingService(
            GeocodeCacheRepository cacheRepository, @Value("${geo.google-api-key:}") String apiKey) {
        this.cacheRepository = cacheRepository;
        this.apiKey = apiKey;
        this.restClient = RestClient.builder().baseUrl("https://maps.googleapis.com/maps/api").build();
    }

    @Transactional
    public GeocodeResult geocode(String address) {
        String normalized = normalize(address);
        String hash = sha256(normalized);

        return cacheRepository.findByAddressHash(hash)
                .map(cache -> new GeocodeResult(cache.getStatus(), cache.getLatitude(), cache.getLongitude()))
                .orElseGet(() -> callAndCache(normalized, hash));
    }

    private GeocodeResult callAndCache(String normalized, String hash) {
        if (apiKey == null || apiKey.isBlank()) {
            return new GeocodeResult("NOT_CONFIGURED", null, null);
        }

        try {
            Map<?, ?> response = restClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/geocode/json")
                            .queryParam("address", normalized)
                            .queryParam("key", apiKey)
                            .build())
                    .retrieve()
                    .body(Map.class);

            String status = (String) response.get("status");
            List<?> results = (List<?>) response.get("results");

            Double lat = null;
            Double lng = null;
            if ("OK".equals(status) && results != null && !results.isEmpty()) {
                Map<?, ?> geometry = (Map<?, ?>) ((Map<?, ?>) results.get(0)).get("geometry");
                Map<?, ?> location = (Map<?, ?>) geometry.get("location");
                lat = ((Number) location.get("lat")).doubleValue();
                lng = ((Number) location.get("lng")).doubleValue();
            }

            if (!RETRYABLE_STATUSES.contains(status)) {
                GeocodeCache cache = new GeocodeCache();
                cache.setAddressHash(hash);
                cache.setNormalizedAddress(normalized);
                cache.setLatitude(lat);
                cache.setLongitude(lng);
                cache.setStatus(status);
                cacheRepository.save(cache);
            }

            return new GeocodeResult(status, lat, lng);
        } catch (Exception ex) {
            log.error("Geocoding request failed for '{}'", normalized, ex);
            return new GeocodeResult("UNKNOWN_ERROR", null, null);
        }
    }

    private String normalize(String address) {
        return address.trim().toLowerCase().replaceAll("\\s+", " ");
    }

    private String sha256(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return HexFormat.of().formatHex(digest.digest(value.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception ex) {
            throw new IllegalStateException("SHA-256 not available", ex);
        }
    }
}
