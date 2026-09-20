-- Same "who runs this + how to reach them" pattern as Travel and
-- Calisthenics, applied to Fitness, Sports Activity, Meet Friends
-- Nearby, and Shopping (which also gains a location, so "near me"
-- search means something for a store/seller too).

ALTER TABLE fitness_sessions ADD COLUMN gym_name VARCHAR(150);
ALTER TABLE fitness_sessions ADD COLUMN trainer_phone VARCHAR(30);
ALTER TABLE fitness_sessions ADD COLUMN trainer_email VARCHAR(150);
ALTER TABLE fitness_sessions ADD COLUMN trainer_photo_url VARCHAR(500);

ALTER TABLE sports_activities ADD COLUMN venue_name VARCHAR(150);
ALTER TABLE sports_activities ADD COLUMN organizer_name VARCHAR(150);
ALTER TABLE sports_activities ADD COLUMN organizer_phone VARCHAR(30);
ALTER TABLE sports_activities ADD COLUMN organizer_email VARCHAR(150);
ALTER TABLE sports_activities ADD COLUMN organizer_photo_url VARCHAR(500);

ALTER TABLE friend_meetups ADD COLUMN venue_name VARCHAR(150);
ALTER TABLE friend_meetups ADD COLUMN host_name VARCHAR(150);
ALTER TABLE friend_meetups ADD COLUMN host_phone VARCHAR(30);
ALTER TABLE friend_meetups ADD COLUMN host_email VARCHAR(150);
ALTER TABLE friend_meetups ADD COLUMN host_photo_url VARCHAR(500);

ALTER TABLE shopping_products ADD COLUMN location VARCHAR(255);
ALTER TABLE shopping_products ADD COLUMN store_name VARCHAR(150);
ALTER TABLE shopping_products ADD COLUMN store_contact_name VARCHAR(150);
ALTER TABLE shopping_products ADD COLUMN store_phone VARCHAR(30);
ALTER TABLE shopping_products ADD COLUMN store_email VARCHAR(150);
ALTER TABLE shopping_products ADD COLUMN store_photo_url VARCHAR(500);

-- Backfill existing rows with contact info, then seed a few more per
-- module in different areas so area search has something to find.

UPDATE fitness_sessions SET
    gym_name = 'Central Park Yoga Studio', trainer_phone = '+91 98450 66771',
    trainer_email = 'hello@centralparkyoga.example', trainer_photo_url = 'https://i.pravatar.cc/150?img=21'
WHERE title = 'Morning Park Yoga';
UPDATE fitness_sessions SET
    gym_name = 'Lakeview HIIT Club', trainer_phone = '+91 90040 33221',
    trainer_email = 'train@lakeviewhiit.example', trainer_photo_url = 'https://i.pravatar.cc/150?img=8'
WHERE title = 'HIIT Bootcamp by the Lake';

INSERT INTO fitness_sessions (
    title, description, location, schedule_text, price_label, tag, image_urls, image_url,
    trainer_name, difficulty_level, status,
    gym_name, trainer_phone, trainer_email, trainer_photo_url
) VALUES
(
    'Powerlifting Fundamentals', 'Learn squat, bench and deadlift technique from a certified strength coach.',
    'Indiranagar, Bangalore', 'Mon, Wed, Fri, 7:00 PM', '₹799/session', 'Strength training',
    '["https://images.unsplash.com/photo-1517836357463-d25dfeac3438?w=800&h=500&fit=crop"]',
    'https://images.unsplash.com/photo-1517836357463-d25dfeac3438?w=800&h=500&fit=crop',
    'Rohan Mehta', 'INTERMEDIATE', 'PUBLISHED',
    'Ironclad Strength Bangalore', '+91 99860 12233', 'rohan@ironcladstrength.example', 'https://i.pravatar.cc/150?img=17'
),
(
    'Sunrise Beach Fitness', 'Bodyweight circuit training on the sand — no equipment needed.',
    'Bandra, Mumbai', 'Tue, Thu, Sat, 6:00 AM', '₹299/session', 'Outdoor',
    '["https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?w=800&h=500&fit=crop"]',
    'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?w=800&h=500&fit=crop',
    'Neha Joshi', 'BEGINNER', 'PUBLISHED',
    'Bandra Beach Fitness Co.', '+91 98200 44556', 'neha@bandrabeachfitness.example', 'https://i.pravatar.cc/150?img=29'
);

-- Sports activities
UPDATE sports_activities SET
    venue_name = 'Turf Arena Sector 9', organizer_name = 'Deepak Verma',
    organizer_phone = '+91 98110 77441', organizer_email = 'deepak@turfarena9.example',
    organizer_photo_url = 'https://i.pravatar.cc/150?img=18'
WHERE title = '5-a-side Football League';
UPDATE sports_activities SET
    venue_name = 'Indoor Sports Complex', organizer_name = 'Meena Iyer',
    organizer_phone = '+91 96540 22119', organizer_email = 'meena@indoorsportscomplex.example',
    organizer_photo_url = 'https://i.pravatar.cc/150?img=36'
WHERE title = 'Badminton Doubles Ladder';

INSERT INTO sports_activities (
    title, description, location, schedule_text, price_label, tag, image_urls, image_url,
    sport_type, team_size, status,
    venue_name, organizer_name, organizer_phone, organizer_email, organizer_photo_url
) VALUES
(
    'Weekend Cricket Nets', 'Practice nets with a certified coach — batting, bowling and fielding drills.',
    'HSR Layout, Bangalore', 'Sat & Sun, 7:00 AM', '₹350/session', 'All levels',
    '["https://images.unsplash.com/photo-1531415074968-036ba1b575da?w=800&h=500&fit=crop"]',
    'https://images.unsplash.com/photo-1531415074968-036ba1b575da?w=800&h=500&fit=crop',
    'Cricket', 11, 'PUBLISHED',
    'HSR Cricket Academy', 'Arjun Nair', '+91 99720 55123', 'arjun@hsrcricket.example', 'https://i.pravatar.cc/150?img=41'
),
(
    'Basketball Pickup Games', 'Casual 5-on-5 pickup games, all skill levels welcome.',
    'Powai, Mumbai', 'Every evening, 7–9 PM', '₹100/head', 'Drop-in',
    '["https://images.unsplash.com/photo-1546519638-68e109498ffc?w=800&h=500&fit=crop"]',
    'https://images.unsplash.com/photo-1546519638-68e109498ffc?w=800&h=500&fit=crop',
    'Basketball', 5, 'PUBLISHED',
    'Powai Basketball Court', 'Farhan Sheikh', '+91 98330 66778', 'farhan@powaibball.example', 'https://i.pravatar.cc/150?img=52'
);

-- Friend meetups
UPDATE friend_meetups SET
    venue_name = 'Cafe Mosaic', host_name = 'Ritu Kapoor',
    host_phone = '+91 97170 88992', host_email = 'ritu@cafemosaicmeetups.example',
    host_photo_url = 'https://i.pravatar.cc/150?img=44'
WHERE title = 'Board Game Night for New Faces';

INSERT INTO friend_meetups (
    title, description, location, schedule_text, price_label, tag, image_urls, image_url,
    min_age, max_participants, status,
    venue_name, host_name, host_phone, host_email, host_photo_url
) VALUES
(
    'Sunday Hiking & Chai Club', 'Easy group hike followed by chai and conversation — great for meeting new people.',
    'Nandi Hills, Bangalore', 'Every Sunday, 6:30 AM', '₹199', 'Outdoors',
    '["https://images.unsplash.com/photo-1551632811-561732d1e306?w=800&h=500&fit=crop"]',
    'https://images.unsplash.com/photo-1551632811-561732d1e306?w=800&h=500&fit=crop',
    21, 25, 'PUBLISHED',
    'Nandi Hills Trailhead', 'Kabir Malhotra', '+91 99010 33445', 'kabir@sundayhikingclub.example', 'https://i.pravatar.cc/150?img=56'
),
(
    'Book Lovers'' Meetup', 'Monthly book discussion over coffee — new members always welcome.',
    'Bandra, Mumbai', 'Last Saturday of the month, 5:00 PM', 'Free entry', 'Book club',
    '["https://images.unsplash.com/photo-1521587760476-6c12a4b040da?w=800&h=500&fit=crop"]',
    'https://images.unsplash.com/photo-1521587760476-6c12a4b040da?w=800&h=500&fit=crop',
    18, 20, 'PUBLISHED',
    'Kitab Khana Cafe', 'Alia Sen', '+91 98920 11667', 'alia@booklovers.example', 'https://i.pravatar.cc/150?img=25'
);

-- Shopping: add location + store contact to existing catalog and a
-- couple more items in different cities.
UPDATE shopping_products SET
    location = 'Koramangala, Bangalore', store_name = 'Green Basket Grocers',
    store_contact_name = 'Suman Rao', store_phone = '+91 98450 99001',
    store_email = 'orders@greenbasket.example', store_photo_url = 'https://i.pravatar.cc/150?img=9'
WHERE title IN ('Fresh Fruit Basket', 'Organic Vegetable Pack');

UPDATE shopping_products SET
    location = 'Phoenix Marketcity, Mumbai', store_name = 'City Mall Electronics',
    store_contact_name = 'Vishal Kumar', store_phone = '+91 99870 44112',
    store_email = 'sales@citymallelectronics.example', store_photo_url = 'https://i.pravatar.cc/150?img=13'
WHERE title IN ('Wireless Earbuds', 'Leather Wallet', 'Smartwatch');
