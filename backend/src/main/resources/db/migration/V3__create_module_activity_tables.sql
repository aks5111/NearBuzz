-- One table per activity module (Section 3/9 of PROJECT_STRUCTURE_GUIDE.md).
-- Shared "listing" shape: title, description, location, schedule_text,
-- price_label, tag, image_url, status, created_by, created_at, updated_at.
-- Each also gets a couple of module-specific columns.

CREATE TABLE travel_packages (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    location VARCHAR(255) NOT NULL,
    schedule_text VARCHAR(255),
    price_label VARCHAR(50),
    tag VARCHAR(100),
    image_url VARCHAR(500),
    duration_days INT,
    group_size INT,
    status VARCHAR(20) NOT NULL DEFAULT 'PUBLISHED'
        CHECK (status IN ('DRAFT', 'PUBLISHED', 'ARCHIVED')),
    created_by BIGINT REFERENCES users(id),
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE fitness_sessions (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    location VARCHAR(255) NOT NULL,
    schedule_text VARCHAR(255),
    price_label VARCHAR(50),
    tag VARCHAR(100),
    image_url VARCHAR(500),
    trainer_name VARCHAR(150),
    difficulty_level VARCHAR(20)
        CHECK (difficulty_level IN ('BEGINNER', 'INTERMEDIATE', 'ADVANCED')),
    status VARCHAR(20) NOT NULL DEFAULT 'PUBLISHED'
        CHECK (status IN ('DRAFT', 'PUBLISHED', 'ARCHIVED')),
    created_by BIGINT REFERENCES users(id),
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE calisthenics_sessions (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    location VARCHAR(255) NOT NULL,
    schedule_text VARCHAR(255),
    price_label VARCHAR(50),
    tag VARCHAR(100),
    image_url VARCHAR(500),
    difficulty_level VARCHAR(20)
        CHECK (difficulty_level IN ('BEGINNER', 'INTERMEDIATE', 'ADVANCED')),
    status VARCHAR(20) NOT NULL DEFAULT 'PUBLISHED'
        CHECK (status IN ('DRAFT', 'PUBLISHED', 'ARCHIVED')),
    created_by BIGINT REFERENCES users(id),
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE party_places (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    location VARCHAR(255) NOT NULL,
    schedule_text VARCHAR(255),
    price_label VARCHAR(50),
    tag VARCHAR(100),
    image_url VARCHAR(500),
    capacity INT,
    status VARCHAR(20) NOT NULL DEFAULT 'PUBLISHED'
        CHECK (status IN ('DRAFT', 'PUBLISHED', 'ARCHIVED')),
    created_by BIGINT REFERENCES users(id),
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE friend_meetups (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    location VARCHAR(255) NOT NULL,
    schedule_text VARCHAR(255),
    price_label VARCHAR(50),
    tag VARCHAR(100),
    image_url VARCHAR(500),
    min_age INT,
    max_participants INT,
    status VARCHAR(20) NOT NULL DEFAULT 'PUBLISHED'
        CHECK (status IN ('DRAFT', 'PUBLISHED', 'ARCHIVED')),
    created_by BIGINT REFERENCES users(id),
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE sports_activities (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    location VARCHAR(255) NOT NULL,
    schedule_text VARCHAR(255),
    price_label VARCHAR(50),
    tag VARCHAR(100),
    image_url VARCHAR(500),
    sport_type VARCHAR(100),
    team_size INT,
    status VARCHAR(20) NOT NULL DEFAULT 'PUBLISHED'
        CHECK (status IN ('DRAFT', 'PUBLISHED', 'ARCHIVED')),
    created_by BIGINT REFERENCES users(id),
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE events (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    location VARCHAR(255) NOT NULL,
    schedule_text VARCHAR(255),
    price_label VARCHAR(50),
    tag VARCHAR(100),
    image_url VARCHAR(500),
    organizer_name VARCHAR(150),
    total_seats INT,
    seats_booked INT NOT NULL DEFAULT 0,
    status VARCHAR(20) NOT NULL DEFAULT 'PUBLISHED'
        CHECK (status IN ('DRAFT', 'PUBLISHED', 'ARCHIVED')),
    created_by BIGINT REFERENCES users(id),
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);
