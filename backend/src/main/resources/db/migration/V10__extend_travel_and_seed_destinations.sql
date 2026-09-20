-- Travel packages get agency/contact info + what's included, so the
-- public travel page can show "who runs this trip" like real travel
-- booking sites (adventurebuddha, coorgtour, etc.) do.
ALTER TABLE travel_packages ADD COLUMN inclusions TEXT;
ALTER TABLE travel_packages ADD COLUMN agency_name VARCHAR(150);
ALTER TABLE travel_packages ADD COLUMN agency_contact_name VARCHAR(150);
ALTER TABLE travel_packages ADD COLUMN agency_phone VARCHAR(30);
ALTER TABLE travel_packages ADD COLUMN agency_email VARCHAR(150);
ALTER TABLE travel_packages ADD COLUMN agency_photo_url VARCHAR(500);

INSERT INTO travel_packages (
    title, description, location, schedule_text, price_label, tag, image_urls, image_url,
    duration_days, group_size, latitude, longitude,
    inclusions, agency_name, agency_contact_name, agency_phone, agency_email, agency_photo_url
) VALUES
(
    'Coorg Mandalpatti Weekend Getaway', 'A quick escape into the Western Ghats — misty coffee estates, waterfalls and a jeep ride up to Mandalpatti viewpoint.',
    'Coorg, Karnataka', 'Every Friday night departure, 2 days', '₹5,499', 'Bestseller',
    '["https://images.unsplash.com/photo-1602216056096-3b40cc0c9944?w=800&h=500&fit=crop"]',
    'https://images.unsplash.com/photo-1602216056096-3b40cc0c9944?w=800&h=500&fit=crop',
    2, 15, 12.3375, 75.8069,
    'Pickup & drop from Bangalore, 1 night homestay, breakfast & dinner, Mandalpatti jeep safari, Abbey Falls & Namdroling Monastery sightseeing, trained trek guide',
    'Coorg Holiday Trails', 'Ravi Kumar', '+91 98450 12345', 'bookings@coorgholidaytrails.example',
    'https://i.pravatar.cc/150?img=12'
),
(
    'Wayanad Rainforest Retreat', 'Trek through cloud forests, explore ancient caves and unwind by a bonfire in Kerala''s green heartland.',
    'Wayanad, Kerala', 'Departs Thu & Sat, 3 days', '₹6,999', 'Nature escape',
    '["https://images.unsplash.com/photo-1580289142403-de1b6f9c1a8c?w=800&h=500&fit=crop"]',
    'https://images.unsplash.com/photo-1580289142403-de1b6f9c1a8c?w=800&h=500&fit=crop',
    3, 10, 11.6854, 76.1320,
    'AC cabs, 2 nights resort stay, all meals, Edakkal Caves & Chembra Peak trek, bonfire & folk dance evening',
    'Wayanad Nature Tours', 'Anjali Menon', '+91 94470 65432', 'hello@wayanadnaturetours.example',
    'https://i.pravatar.cc/150?img=32'
),
(
    'Sahyadri Monsoon Trek near Pune', 'A one-day monsoon trek in the Sahyadris — waterfalls, green valleys and hot chai at the top.',
    'Lonavala, Pune', 'Every Sunday, 6:00 AM', '₹1,499', 'Day trip',
    '["https://images.unsplash.com/photo-1544735716-392fe2489ffa?w=800&h=500&fit=crop"]',
    'https://images.unsplash.com/photo-1544735716-392fe2489ffa?w=800&h=500&fit=crop',
    1, 20, 18.7537, 73.4068,
    'Pickup from Pune, breakfast, trek guide, waterfall visit, forest permit fees',
    'Sahyadri Trekkers Club', 'Suresh Patil', '+91 90210 11223', 'info@sahyadritrekkers.example',
    'https://i.pravatar.cc/150?img=51'
),
(
    'Kodaikanal Hills Escape', 'A relaxed hill-station holiday — lake boating, pine forests and viewpoints over the Palani Hills.',
    'Kodaikanal, Tamil Nadu', 'Departs Fri, 4 days', '₹8,499', 'Family friendly',
    '["https://images.unsplash.com/photo-1626621341169-4a1b45ff6cb5?w=800&h=500&fit=crop"]',
    'https://images.unsplash.com/photo-1626621341169-4a1b45ff6cb5?w=800&h=500&fit=crop',
    4, 12, 10.2381, 77.4892,
    'AC coach travel, 3 nights hotel stay, all meals, boating at Kodai Lake, Pillar Rocks & Coaker''s Walk sightseeing, bonfire evening',
    'Anusha Holidays', 'Priya Raman', '+91 98765 43210', 'care@anushaholidays.example',
    'https://i.pravatar.cc/150?img=45'
);

UPDATE travel_packages SET
    inclusions = 'Pickup & drop, guide, breakfast, entry fees',
    agency_name = 'Bhandardara Trek Co.', agency_contact_name = 'Amit Sawant',
    agency_phone = '+91 99870 22334', agency_email = 'trips@bhandardaratrek.example',
    agency_photo_url = 'https://i.pravatar.cc/150?img=15'
WHERE title = 'Sunrise Trek to Kalsubai Peak';

UPDATE travel_packages SET
    inclusions = 'Return flights not included, AC stay, breakfast, beach activities, airport transfers',
    agency_name = 'Goa Beach Holidays', agency_contact_name = 'Fernanda D''Souza',
    agency_phone = '+91 98221 55667', agency_email = 'stay@goabeachholidays.example',
    agency_photo_url = 'https://i.pravatar.cc/150?img=47'
WHERE title = 'Weekend Goa Beach Getaway';
