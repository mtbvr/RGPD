-- =========================
-- ROLES
-- =========================
INSERT INTO profile (id, name) VALUES
                                 (1, 'ADMIN'),
                                 (2, 'TEACHER'),
                                 (3, 'STAFF')
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
-- ROLE_RIGHTS
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