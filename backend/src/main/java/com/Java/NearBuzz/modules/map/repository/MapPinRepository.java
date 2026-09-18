package com.Java.NearBuzz.modules.map.repository;

import com.Java.NearBuzz.modules.map.dto.MapPinResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Reads pins for the map by UNION-ing the seven module tables directly via
 * JdbcTemplate, rather than giving each one a full JPA entity/repository
 * stack — those modules don't have real CRUD backends yet (see the admin
 * "coming soon" placeholders), so this stays a thin, read-only projection
 * until each module gets built out properly.
 */
@Repository
public class MapPinRepository {

    private static final String SQL = """
            SELECT 'travel' AS module, id, title, location, schedule_text, price_label, image_url, latitude, longitude
                FROM travel_packages WHERE status = 'PUBLISHED' AND latitude IS NOT NULL
            UNION ALL
            SELECT 'fitness', id, title, location, schedule_text, price_label, image_url, latitude, longitude
                FROM fitness_sessions WHERE status = 'PUBLISHED' AND latitude IS NOT NULL
            UNION ALL
            SELECT 'calisthenics', id, title, location, schedule_text, price_label, image_url, latitude, longitude
                FROM calisthenics_sessions WHERE status = 'PUBLISHED' AND latitude IS NOT NULL
            UNION ALL
            SELECT 'partyplace', id, title, location, schedule_text, price_label, image_url, latitude, longitude
                FROM party_places WHERE status = 'PUBLISHED' AND latitude IS NOT NULL
            UNION ALL
            SELECT 'friendsnearby', id, title, location, schedule_text, price_label, image_url, latitude, longitude
                FROM friend_meetups WHERE status = 'PUBLISHED' AND latitude IS NOT NULL
            UNION ALL
            SELECT 'sportsactivity', id, title, location, schedule_text, price_label, image_url, latitude, longitude
                FROM sports_activities WHERE status = 'PUBLISHED' AND latitude IS NOT NULL
            UNION ALL
            SELECT 'eventbooking', id, title, location, schedule_text, price_label, image_url, latitude, longitude
                FROM events WHERE status = 'PUBLISHED' AND latitude IS NOT NULL
            """;

    private final JdbcTemplate jdbcTemplate;

    public MapPinRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<MapPinResponse> findAllPins() {
        return jdbcTemplate.query(SQL, (rs, rowNum) -> new MapPinResponse(
                rs.getLong("id"),
                rs.getString("module"),
                rs.getString("title"),
                rs.getString("location"),
                rs.getString("schedule_text"),
                rs.getString("price_label"),
                rs.getString("image_url"),
                rs.getObject("latitude", Double.class),
                rs.getObject("longitude", Double.class)));
    }
}
