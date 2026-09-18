-- RBAC groundwork. users.role (V2) stays the source of truth for what
-- Spring Security authenticates with (ROLE_USER/ROLE_ADMIN/ROLE_SUPER_ADMIN
-- as a JWT claim) — these tables are the admin-manageable picture of what
-- each of those three roles is allowed to do, surfaced in the admin panel's
-- Roles & Permissions screen. Fine-grained enforcement per permission is a
-- later step; today's authorization still checks the users.role claim.

CREATE TABLE roles (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL UNIQUE
        CHECK (name IN ('ROLE_USER', 'ROLE_ADMIN', 'ROLE_SUPER_ADMIN')),
    description VARCHAR(255) NOT NULL
);

CREATE TABLE permissions (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(60) NOT NULL UNIQUE,
    module VARCHAR(30) NOT NULL,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE role_permissions (
    role_id BIGINT NOT NULL REFERENCES roles(id) ON DELETE CASCADE,
    permission_id BIGINT NOT NULL REFERENCES permissions(id) ON DELETE CASCADE,
    PRIMARY KEY (role_id, permission_id)
);

INSERT INTO roles (name, description) VALUES
    ('ROLE_USER', 'Regular customer using the public site'),
    ('ROLE_ADMIN', 'Manages listings and bookings day-to-day'),
    ('ROLE_SUPER_ADMIN', 'Full access, including user and role management');

INSERT INTO permissions (code, module, description) VALUES
    ('travel.view', 'travel', 'View travel listings'),
    ('travel.manage', 'travel', 'Create, edit and delete travel listings'),
    ('fitness.view', 'fitness', 'View fitness listings'),
    ('fitness.manage', 'fitness', 'Create, edit and delete fitness listings'),
    ('calisthenics.view', 'calisthenics', 'View calisthenics listings'),
    ('calisthenics.manage', 'calisthenics', 'Create, edit and delete calisthenics listings'),
    ('partyplace.view', 'partyplace', 'View party place listings'),
    ('partyplace.manage', 'partyplace', 'Create, edit and delete party place listings'),
    ('friendsnearby.view', 'friendsnearby', 'View meet-friends-nearby listings'),
    ('friendsnearby.manage', 'friendsnearby', 'Create, edit and delete meet-friends-nearby listings'),
    ('sportsactivity.view', 'sportsactivity', 'View sports activity listings'),
    ('sportsactivity.manage', 'sportsactivity', 'Create, edit and delete sports activity listings'),
    ('eventbooking.view', 'eventbooking', 'View event booking listings'),
    ('eventbooking.manage', 'eventbooking', 'Create, edit and delete event booking listings'),
    ('bookings.view', 'bookings', 'View booking/join requests across modules'),
    ('bookings.manage', 'bookings', 'Approve or reject booking/join requests'),
    ('users.view', 'users', 'View the user list'),
    ('users.manage', 'users', 'Block/unblock users and change their role'),
    ('settings.manage', 'settings', 'Edit categories, tags and other master data'),
    ('roles.manage', 'roles', 'Edit which permissions each role has');

-- SUPER_ADMIN: everything.
INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id FROM roles r CROSS JOIN permissions p WHERE r.name = 'ROLE_SUPER_ADMIN';

-- ADMIN: every module's view+manage, bookings, settings, and users.view —
-- but not users.manage or roles.manage (only a super admin controls
-- who has access and what access means).
INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id FROM roles r CROSS JOIN permissions p
WHERE r.name = 'ROLE_ADMIN'
  AND p.code NOT IN ('users.manage', 'roles.manage');

-- USER: no admin-panel permissions.
