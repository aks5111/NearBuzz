-- Adds a JSON-array-in-TEXT column for multiple images per listing,
-- alongside the existing single image_url (kept as the "cover" image —
-- first of image_urls — for anything that still only shows one photo,
-- e.g. map pin markers, the shopping product grid).
ALTER TABLE travel_packages ADD COLUMN image_urls TEXT NOT NULL DEFAULT '[]';
ALTER TABLE fitness_sessions ADD COLUMN image_urls TEXT NOT NULL DEFAULT '[]';
ALTER TABLE calisthenics_sessions ADD COLUMN image_urls TEXT NOT NULL DEFAULT '[]';
ALTER TABLE party_places ADD COLUMN image_urls TEXT NOT NULL DEFAULT '[]';
ALTER TABLE friend_meetups ADD COLUMN image_urls TEXT NOT NULL DEFAULT '[]';
ALTER TABLE sports_activities ADD COLUMN image_urls TEXT NOT NULL DEFAULT '[]';
ALTER TABLE events ADD COLUMN image_urls TEXT NOT NULL DEFAULT '[]';
ALTER TABLE shopping_products ADD COLUMN image_urls TEXT NOT NULL DEFAULT '[]';

UPDATE travel_packages SET image_urls = to_json(ARRAY[image_url])::text WHERE image_url IS NOT NULL;
UPDATE fitness_sessions SET image_urls = to_json(ARRAY[image_url])::text WHERE image_url IS NOT NULL;
UPDATE calisthenics_sessions SET image_urls = to_json(ARRAY[image_url])::text WHERE image_url IS NOT NULL;
UPDATE party_places SET image_urls = to_json(ARRAY[image_url])::text WHERE image_url IS NOT NULL;
UPDATE friend_meetups SET image_urls = to_json(ARRAY[image_url])::text WHERE image_url IS NOT NULL;
UPDATE sports_activities SET image_urls = to_json(ARRAY[image_url])::text WHERE image_url IS NOT NULL;
UPDATE events SET image_urls = to_json(ARRAY[image_url])::text WHERE image_url IS NOT NULL;
UPDATE shopping_products SET image_urls = to_json(ARRAY[image_url])::text WHERE image_url IS NOT NULL;
