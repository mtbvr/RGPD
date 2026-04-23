-- =========================
-- PROFILES
-- =========================
INSERT INTO profile (id, name) VALUES
    (1, 'ADMIN'),
    (2, 'TEACHER'),
    (3, 'STAFF'),
    (4, 'STUDENT')
ON CONFLICT (id) DO NOTHING;

-- =========================
-- RIGHTS (RGPD)
-- =========================
INSERT INTO rights (id, name) VALUES
    (1, 'ACCESS_DATA'),
    (2, 'RECTIFY_DATA'),
    (3, 'DELETE_DATA'),
    (4, 'EXPORT_DATA'),
    (5, 'ACCESS_SENSITIVE_DATA'),
    (6, 'RECTIFY_SENSITIVE_DATA'),
    (7, 'DELETE_SENSITIVE_DATA'),
    (8, 'EXPORT_SENSITIVE_DATA')
ON CONFLICT (id) DO NOTHING;

-- =========================
-- PROFILE_RIGHTS
-- =========================

-- ADMIN = full RGPD
INSERT INTO profile_right (profile_id, right_id) VALUES
    (1,1),(1,2),(1,3),(1,4),
    (1,5),(1,6),(1,7),(1,8)
ON CONFLICT DO NOTHING;

-- TEACHER = accès + modification
INSERT INTO profile_right (profile_id, right_id) VALUES
    (2,1),(2,2),
    (2,5),(2,6)
ON CONFLICT DO NOTHING;

-- STAFF = accès uniquement
INSERT INTO profile_right (profile_id, right_id) VALUES
    (3,1),
    (3,5)
ON CONFLICT DO NOTHING;

-- STUDENT = accès à soi-même uniquement (contrôlé applicativement)
INSERT INTO profile_right (profile_id, right_id) VALUES
    (4,1),
    (4,5)
ON CONFLICT DO NOTHING;

-- =========================
-- USERS
-- =========================
-- Mot de passe = "password" encodé BCrypt
INSERT INTO users (id, login, password, student_identity_id, created_at, updated_at) VALUES
    -- Admin
    (1, 'admin@test.com',   '$2a$10$hASgi.8vm4Tb1.xg4s3c2.hWRpd4KoYak14fKKGEZcpKPU9lmQ7cq', NULL, NOW(), NOW()),
    -- Teachers
    (2, 'teacher1@test.com','$2a$10$hASgi.8vm4Tb1.xg4s3c2.hWRpd4KoYak14fKKGEZcpKPU9lmQ7cq', NULL, NOW(), NOW()),
    (3, 'teacher2@test.com','$2a$10$hASgi.8vm4Tb1.xg4s3c2.hWRpd4KoYak14fKKGEZcpKPU9lmQ7cq', NULL, NOW(), NOW()),
    -- Staff
    (4, 'staff1@test.com',  '$2a$10$hASgi.8vm4Tb1.xg4s3c2.hWRpd4KoYak14fKKGEZcpKPU9lmQ7cq', NULL, NOW(), NOW()),
    (5, 'staff2@test.com',  '$2a$10$hASgi.8vm4Tb1.xg4s3c2.hWRpd4KoYak14fKKGEZcpKPU9lmQ7cq', NULL, NOW(), NOW()),
    -- Students (student_identity_id = id dans la table student_identity)
    (6,  'alice@test.com',   '$2a$10$hASgi.8vm4Tb1.xg4s3c2.hWRpd4KoYak14fKKGEZcpKPU9lmQ7cq', 1,  NOW(), NOW()),
    (7,  'bob@test.com',     '$2a$10$hASgi.8vm4Tb1.xg4s3c2.hWRpd4KoYak14fKKGEZcpKPU9lmQ7cq', 2,  NOW(), NOW()),
    (8,  'charlie@test.com', '$2a$10$hASgi.8vm4Tb1.xg4s3c2.hWRpd4KoYak14fKKGEZcpKPU9lmQ7cq', 3,  NOW(), NOW()),
    (9,  'david@test.com',   '$2a$10$hASgi.8vm4Tb1.xg4s3c2.hWRpd4KoYak14fKKGEZcpKPU9lmQ7cq', 4,  NOW(), NOW()),
    (10, 'emma@test.com',    '$2a$10$hASgi.8vm4Tb1.xg4s3c2.hWRpd4KoYak14fKKGEZcpKPU9lmQ7cq', 5,  NOW(), NOW()),
    (11, 'lucas@test.com',   '$2a$10$hASgi.8vm4Tb1.xg4s3c2.hWRpd4KoYak14fKKGEZcpKPU9lmQ7cq', 6,  NOW(), NOW()),
    (12, 'lina@test.com',    '$2a$10$hASgi.8vm4Tb1.xg4s3c2.hWRpd4KoYak14fKKGEZcpKPU9lmQ7cq', 7,  NOW(), NOW()),
    (13, 'hugo@test.com',    '$2a$10$hASgi.8vm4Tb1.xg4s3c2.hWRpd4KoYak14fKKGEZcpKPU9lmQ7cq', 8,  NOW(), NOW()),
    (14, 'jade@test.com',    '$2a$10$hASgi.8vm4Tb1.xg4s3c2.hWRpd4KoYak14fKKGEZcpKPU9lmQ7cq', 9,  NOW(), NOW()),
    (15, 'leo@test.com',     '$2a$10$hASgi.8vm4Tb1.xg4s3c2.hWRpd4KoYak14fKKGEZcpKPU9lmQ7cq', 10, NOW(), NOW())
ON CONFLICT (id) DO NOTHING;

-- =========================
-- PROFILE_USER
-- =========================
INSERT INTO profile_user (user_id, profile_id) VALUES
    (1,  1), -- admin     -> ADMIN
    (2,  2), -- teacher1  -> TEACHER
    (3,  2), -- teacher2  -> TEACHER
    (4,  3), -- staff1    -> STAFF
    (5,  3), -- staff2    -> STAFF
    (6,  4), -- alice     -> STUDENT
    (7,  4), -- bob       -> STUDENT
    (8,  4), -- charlie   -> STUDENT
    (9,  4), -- david     -> STUDENT
    (10, 4), -- emma      -> STUDENT
    (11, 4), -- lucas     -> STUDENT
    (12, 4), -- lina      -> STUDENT
    (13, 4), -- hugo      -> STUDENT
    (14, 4), -- jade      -> STUDENT
    (15, 4)  -- leo       -> STUDENT
ON CONFLICT DO NOTHING;
