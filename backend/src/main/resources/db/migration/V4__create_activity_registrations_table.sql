-- Generic "book/join" record, one row per user per activity, regardless
-- of which module table the activity lives in (item_id is not FK-enforced
-- since it points at a different table depending on `module`).
CREATE TABLE activity_registrations (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    module VARCHAR(30) NOT NULL
        CHECK (module IN ('travel', 'fitness', 'calisthenics', 'partyplace', 'friendsnearby', 'sportsactivity', 'eventbooking')),
    item_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING'
        CHECK (status IN ('PENDING', 'CONFIRMED', 'CANCELLED')),
    registered_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE INDEX idx_activity_registrations_user ON activity_registrations(user_id);
CREATE INDEX idx_activity_registrations_module_item ON activity_registrations(module, item_id);
