-- V3 only created these tables, never populated them — the "demo
-- activities" people saw were frontend-only mock data (src/modules/home/
-- mock/activities.js), not real rows. V7's coordinate UPDATEs matched
-- nothing because of that. This seeds real rows (mirroring that same
-- frontend mock content, so the two stay visually consistent) with
-- coordinates included directly, so the map has pins immediately.

INSERT INTO travel_packages (title, description, location, schedule_text, price_label, tag, image_url, duration_days, group_size, latitude, longitude) VALUES
    ('Sunrise Trek to Kalsubai Peak', NULL, 'Bhandardara, Maharashtra', 'Every Saturday, 4:00 AM', '₹899', 'Group of 12', 'https://picsum.photos/seed/travel-1/600/400', 1, 12, 19.1663, 73.7538),
    ('Weekend Goa Beach Getaway', NULL, 'Goa', 'Fri–Sun, twice a month', '₹4,999', 'Includes stay', 'https://picsum.photos/seed/travel-2/600/400', 3, 20, 15.2993, 74.1240);

INSERT INTO fitness_sessions (title, description, location, schedule_text, price_label, tag, image_url, trainer_name, difficulty_level, latitude, longitude) VALUES
    ('Morning Park Yoga', NULL, 'Central Park, Sector 21', 'Mon–Sat, 6:30 AM', 'Free', 'All levels', 'https://picsum.photos/seed/fitness-1/600/400', NULL, 'BEGINNER', 18.5308, 73.8475),
    ('HIIT Bootcamp by the Lake', NULL, 'Lakeview Ground', 'Tue, Thu, Sat, 6:00 PM', '₹299/session', 'Trainer-led', 'https://picsum.photos/seed/fitness-2/600/400', NULL, 'INTERMEDIATE', 18.5679, 73.9143);

INSERT INTO calisthenics_sessions (title, description, location, schedule_text, price_label, tag, image_url, difficulty_level, latitude, longitude) VALUES
    ('Street Workout Meetup', NULL, 'Riverside Fitness Bars', 'Every Sunday, 7:00 AM', 'Free', 'Beginner friendly', 'https://picsum.photos/seed/cali-1/600/400', 'BEGINNER', 18.5089, 73.8553);

INSERT INTO party_places (title, description, location, schedule_text, price_label, tag, image_url, capacity, latitude, longitude) VALUES
    ('Rooftop Lounge — The Skyline', NULL, 'MG Road', 'Open Thu–Sun, 7 PM–12 AM', '₹1,500 cover', 'Live DJ', 'https://picsum.photos/seed/party-1/600/400', 150, 18.5605, 73.9169);

INSERT INTO friend_meetups (title, description, location, schedule_text, price_label, tag, image_url, min_age, max_participants, latitude, longitude) VALUES
    ('Board Game Night for New Faces', NULL, 'Cafe Mosaic', 'Every Wednesday, 7:00 PM', 'Free entry', '18+ meetup', 'https://picsum.photos/seed/friends-1/600/400', 18, 30, 18.5246, 73.8786);

INSERT INTO sports_activities (title, description, location, schedule_text, price_label, tag, image_url, sport_type, team_size, latitude, longitude) VALUES
    ('5-a-side Football League', NULL, 'Turf Arena, Sector 9', 'Every evening, 6–9 PM', '₹150/head', 'Teams forming', 'https://picsum.photos/seed/sports-1/600/400', 'Football', 5, 18.5018, 73.8636),
    ('Badminton Doubles Ladder', NULL, 'Indoor Sports Complex', 'Mon, Wed, Fri, 8:00 PM', '₹200/session', 'Ranked ladder', 'https://picsum.photos/seed/sports-2/600/400', 'Badminton', 2, 18.5362, 73.8935);

INSERT INTO events (title, description, location, schedule_text, price_label, tag, image_url, organizer_name, total_seats, latitude, longitude) VALUES
    ('Open Mic — Comedy & Poetry', NULL, 'The Attic Cafe', 'Last Friday of the month', '₹250', 'Limited seats', 'https://picsum.photos/seed/event-1/600/400', NULL, 60, 18.5158, 73.8567),
    ('Indie Music Night', NULL, 'Open Air Amphitheatre', 'This Saturday, 8:00 PM', '₹599', 'Selling fast', 'https://picsum.photos/seed/event-2/600/400', NULL, 300, 18.5793, 73.7365);
