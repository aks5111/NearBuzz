-- Calisthenics gets gym/trainer contact info, same idea as Travel's
-- agency fields — so the public page can show who runs each class.
ALTER TABLE calisthenics_sessions ADD COLUMN gym_name VARCHAR(150);
ALTER TABLE calisthenics_sessions ADD COLUMN trainer_contact_name VARCHAR(150);
ALTER TABLE calisthenics_sessions ADD COLUMN trainer_phone VARCHAR(30);
ALTER TABLE calisthenics_sessions ADD COLUMN trainer_email VARCHAR(150);
ALTER TABLE calisthenics_sessions ADD COLUMN trainer_photo_url VARCHAR(500);

UPDATE calisthenics_sessions SET
    gym_name = 'Riverside Street Fitness',
    trainer_contact_name = 'Vikram Nair',
    trainer_phone = '+91 98230 44556',
    trainer_email = 'vikram@riversidestreetfitness.example',
    trainer_photo_url = 'https://i.pravatar.cc/150?img=33'
WHERE title = 'Street Workout Meetup';

INSERT INTO calisthenics_sessions (
    title, description, location, schedule_text, price_label, tag, image_urls, image_url,
    difficulty_level, latitude, longitude, status,
    gym_name, trainer_contact_name, trainer_phone, trainer_email, trainer_photo_url
) VALUES
(
    'Bar Athletes Calisthenics Park', 'Structured pull-up, dip and muscle-up progressions on an outdoor rig, with a certified coach.',
    'Koramangala, Bangalore', 'Mon, Wed, Fri, 6:30 AM & 6:00 PM', '₹499/class', 'Coach-led',
    '["https://images.unsplash.com/photo-1599058917765-a780eda07a3e?w=800&h=500&fit=crop"]',
    'https://images.unsplash.com/photo-1599058917765-a780eda07a3e?w=800&h=500&fit=crop',
    'INTERMEDIATE', 12.9352, 77.6245, 'PUBLISHED',
    'Bar Athletes Bangalore', 'Karthik Reddy', '+91 99000 22114', 'karthik@barathletes.example',
    'https://i.pravatar.cc/150?img=14'
),
(
    'Handstand & Mobility Fundamentals', 'Beginner-friendly handstand progressions, wrist mobility and core control drills.',
    'Andheri, Mumbai', 'Tue, Thu, Sat, 7:00 AM', '₹399/class', 'Beginner friendly',
    '["https://images.unsplash.com/photo-1518611012118-696072aa579a?w=800&h=500&fit=crop"]',
    'https://images.unsplash.com/photo-1518611012118-696072aa579a?w=800&h=500&fit=crop',
    'BEGINNER', 19.1197, 72.8468, 'PUBLISHED',
    'Mumbai Movement Collective', 'Sneha Kulkarni', '+91 97690 88123', 'sneha@mumbaimovement.example',
    'https://i.pravatar.cc/150?img=26'
),
(
    'Advanced Street Workout Squad', 'Front lever, planche and muscle-up training for athletes already comfortable with pull-ups and dips.',
    'Sector 29, Gurgaon', 'Mon–Sat, 5:30 PM', '₹1,999/month', 'Advanced',
    '["https://images.unsplash.com/photo-1546483875-ad9014c88eba?w=800&h=500&fit=crop"]',
    'https://images.unsplash.com/photo-1546483875-ad9014c88eba?w=800&h=500&fit=crop',
    'ADVANCED', 28.4674, 77.0722, 'PUBLISHED',
    'Gurgaon Calisthenics Crew', 'Aman Chaudhary', '+91 98180 77321', 'aman@gurgaoncalisthenics.example',
    'https://i.pravatar.cc/150?img=59'
);
