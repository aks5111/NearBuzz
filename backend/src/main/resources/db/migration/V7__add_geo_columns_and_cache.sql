-- Adds coordinates to every "happening somewhere" module table so they can
-- be pinned on the map. Shopping products are deliberately excluded —
-- they're catalog items, not places with a location of their own.
ALTER TABLE travel_packages ADD COLUMN latitude DOUBLE PRECISION;
ALTER TABLE travel_packages ADD COLUMN longitude DOUBLE PRECISION;

ALTER TABLE fitness_sessions ADD COLUMN latitude DOUBLE PRECISION;
ALTER TABLE fitness_sessions ADD COLUMN longitude DOUBLE PRECISION;

ALTER TABLE calisthenics_sessions ADD COLUMN latitude DOUBLE PRECISION;
ALTER TABLE calisthenics_sessions ADD COLUMN longitude DOUBLE PRECISION;

ALTER TABLE party_places ADD COLUMN latitude DOUBLE PRECISION;
ALTER TABLE party_places ADD COLUMN longitude DOUBLE PRECISION;

ALTER TABLE friend_meetups ADD COLUMN latitude DOUBLE PRECISION;
ALTER TABLE friend_meetups ADD COLUMN longitude DOUBLE PRECISION;

ALTER TABLE sports_activities ADD COLUMN latitude DOUBLE PRECISION;
ALTER TABLE sports_activities ADD COLUMN longitude DOUBLE PRECISION;

ALTER TABLE events ADD COLUMN latitude DOUBLE PRECISION;
ALTER TABLE events ADD COLUMN longitude DOUBLE PRECISION;

-- Demo coordinates around Pune so the map has pins to show immediately,
-- without needing the Google Geocoding key yet.
UPDATE travel_packages SET latitude = 19.1663, longitude = 73.7538 WHERE title LIKE 'Sunrise Trek%';
UPDATE travel_packages SET latitude = 15.2993, longitude = 74.1240 WHERE title LIKE 'Weekend Goa%';
UPDATE fitness_sessions SET latitude = 18.5308, longitude = 73.8475 WHERE title LIKE 'Morning Park Yoga%';
UPDATE fitness_sessions SET latitude = 18.5679, longitude = 73.9143 WHERE title LIKE 'HIIT Bootcamp%';
UPDATE calisthenics_sessions SET latitude = 18.5089, longitude = 73.8553 WHERE title LIKE 'Street Workout%';
UPDATE party_places SET latitude = 18.5605, longitude = 73.9169 WHERE title LIKE 'Rooftop Lounge%';
UPDATE friend_meetups SET latitude = 18.5246, longitude = 73.8786 WHERE title LIKE 'Board Game Night%';
UPDATE sports_activities SET latitude = 18.5018, longitude = 73.8636 WHERE title LIKE '5-a-side%';
UPDATE sports_activities SET latitude = 18.5362, longitude = 73.8935 WHERE title LIKE 'Badminton%';
UPDATE events SET latitude = 18.5158, longitude = 73.8567 WHERE title LIKE 'Open Mic%';
UPDATE events SET latitude = 18.5793, longitude = 73.7365 WHERE title LIKE 'Indie Music%';

-- Geocoding is expensive and quota-limited, so every normalized address is
-- looked up via Google Geocoding at most once, ever. A retryable failure
-- (quota/deny) is NOT cached, so the next import attempt retries it;
-- ZERO_RESULTS IS cached, since re-asking Google won't change that answer.
CREATE TABLE geo_geocode_cache (
    id BIGSERIAL PRIMARY KEY,
    address_hash VARCHAR(64) NOT NULL UNIQUE,
    normalized_address VARCHAR(500) NOT NULL,
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);
