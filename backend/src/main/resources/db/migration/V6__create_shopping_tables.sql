CREATE TABLE shopping_categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    slug VARCHAR(100) NOT NULL UNIQUE,
    icon VARCHAR(40)
);

CREATE TABLE shopping_products (
    id BIGSERIAL PRIMARY KEY,
    category_id BIGINT NOT NULL REFERENCES shopping_categories(id),
    title VARCHAR(200) NOT NULL,
    description TEXT,
    price_label VARCHAR(50) NOT NULL,
    image_url VARCHAR(500),
    stock_quantity INT NOT NULL DEFAULT 0,
    status VARCHAR(20) NOT NULL DEFAULT 'PUBLISHED'
        CHECK (status IN ('DRAFT', 'PUBLISHED', 'ARCHIVED')),
    created_by BIGINT REFERENCES users(id),
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE INDEX idx_shopping_products_category ON shopping_products(category_id);

INSERT INTO shopping_categories (name, slug, icon) VALUES
    ('Grocery', 'grocery', 'ShoppingBasket'),
    ('Mall', 'mall', 'Store'),
    ('Clothes', 'clothes', 'Shirt'),
    ('Sports', 'sports', 'Trophy'),
    ('Electronics', 'electronics', 'Smartphone'),
    ('Home & Living', 'home-living', 'Sofa'),
    ('Beauty', 'beauty', 'Sparkles');

-- Demo catalog so the page isn't empty before the admin adds real products.
-- Images are stable, direct Unsplash photo URLs (not the source.unsplash.com
-- redirector, which is deprecated) — free to embed, no scraping involved.
INSERT INTO shopping_products (category_id, title, description, price_label, image_url, stock_quantity) VALUES
    ((SELECT id FROM shopping_categories WHERE slug = 'grocery'), 'Fresh Fruit Basket', 'Seasonal mixed fruits, hand-picked daily.', '₹349', 'https://images.unsplash.com/photo-1610832958506-aa56368176cf?w=600&h=400&fit=crop', 40),
    ((SELECT id FROM shopping_categories WHERE slug = 'grocery'), 'Organic Vegetable Pack', 'Farm-fresh vegetables, pesticide-free.', '₹299', 'https://images.unsplash.com/photo-1540420773420-3366772f4999?w=600&h=400&fit=crop', 55),
    ((SELECT id FROM shopping_categories WHERE slug = 'mall'), 'Wireless Earbuds', 'Noise-cancelling, 24hr battery with case.', '₹2,499', 'https://images.unsplash.com/photo-1590658268037-6bf12165a8df?w=600&h=400&fit=crop', 20),
    ((SELECT id FROM shopping_categories WHERE slug = 'mall'), 'Leather Wallet', 'Genuine leather, RFID-blocking.', '₹1,199', 'https://images.unsplash.com/photo-1627123424574-724758594e93?w=600&h=400&fit=crop', 30),
    ((SELECT id FROM shopping_categories WHERE slug = 'clothes'), 'Cotton Casual Shirt', 'Breathable cotton, regular fit.', '₹899', 'https://images.unsplash.com/photo-1602810318383-e386cc2a3ccf?w=600&h=400&fit=crop', 60),
    ((SELECT id FROM shopping_categories WHERE slug = 'clothes'), 'Denim Jacket', 'Classic fit, unisex denim jacket.', '₹1,799', 'https://images.unsplash.com/photo-1543087903-1ac2ec7aa8c5?w=600&h=400&fit=crop', 25),
    ((SELECT id FROM shopping_categories WHERE slug = 'sports'), 'Yoga Mat', 'Non-slip, 6mm cushioned mat.', '₹699', 'https://images.unsplash.com/photo-1601925260368-ae2f83cf8b7f?w=600&h=400&fit=crop', 45),
    ((SELECT id FROM shopping_categories WHERE slug = 'sports'), 'Football', 'Match-quality size 5 football.', '₹999', 'https://images.unsplash.com/photo-1614632537197-38a17061c2bd?w=600&h=400&fit=crop', 35),
    ((SELECT id FROM shopping_categories WHERE slug = 'electronics'), 'Smartwatch', 'Fitness tracking, heart rate, 7-day battery.', '₹3,999', 'https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=600&h=400&fit=crop', 15),
    ((SELECT id FROM shopping_categories WHERE slug = 'home-living'), 'Ceramic Dinner Set', '16-piece dinnerware set.', '₹1,499', 'https://images.unsplash.com/photo-1584346133934-a3afd2e15fd6?w=600&h=400&fit=crop', 20),
    ((SELECT id FROM shopping_categories WHERE slug = 'beauty'), 'Skincare Gift Set', 'Cleanser, toner and moisturizer trio.', '₹1,299', 'https://images.unsplash.com/photo-1556228720-195a672e8a03?w=600&h=400&fit=crop', 28);

INSERT INTO permissions (code, module, description) VALUES
    ('shopping.view', 'shopping', 'View shopping products and categories'),
    ('shopping.manage', 'shopping', 'Create, edit and delete shopping products');

INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id FROM roles r CROSS JOIN permissions p
WHERE r.name = 'ROLE_SUPER_ADMIN' AND p.code IN ('shopping.view', 'shopping.manage');

INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id FROM roles r CROSS JOIN permissions p
WHERE r.name = 'ROLE_ADMIN' AND p.code IN ('shopping.view', 'shopping.manage');
