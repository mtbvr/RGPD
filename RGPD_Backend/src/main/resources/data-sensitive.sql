-- =========================
-- STUDENT_SENSITIVE
-- =========================
INSERT INTO student_sensitive (student_id, allergy, is_anonymized, deleted_at, created_at, updated_at) VALUES
    (1,  'ENCRYPTED_ASTHMA',   false, NULL, NOW(), NOW()),
    (2,  'ENCRYPTED_DIABETES', false, NULL, NOW(), NOW()),
    (3,  NULL,                 false, NULL, NOW(), NOW()),
    (4,  'ENCRYPTED_PEANUT',   false, NULL, NOW(), NOW()),
    (5,  NULL,                 false, NULL, NOW(), NOW()),
    (6,  'ENCRYPTED_DYSLEXIA', false, NULL, NOW(), NOW()),
    (7,  NULL,                 false, NULL, NOW(), NOW()),
    (8,  'ENCRYPTED_ASTHMA',   false, NULL, NOW(), NOW()),
    (9,  NULL,                 true,  NULL, NOW(), NOW()),  -- anonymisé (droit à l'oubli)
    (10, NULL,                 true,  NOW(), NOW(), NOW())  -- supprimé (droit à l'effacement)
ON CONFLICT DO NOTHING;
