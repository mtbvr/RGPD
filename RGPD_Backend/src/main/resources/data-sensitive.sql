-- =========================
-- STUDENT_SENSITIVE DATA
-- =========================

INSERT INTO student_sensitive (
    student_id,
    allergy,
    is_anonymized,
    deleted_at,
    created_at,
    updated_at
) VALUES

-- données normales (chiffrées fake)
(1, 'ENCRYPTED_ASTHMA', false, NULL, NOW(), NOW()),
(2, 'ENCRYPTED_DIABETES', false, NULL, NOW(), NOW()),
(3, NULL, false, NULL, NOW(), NOW()),
(4, 'ENCRYPTED_PEANUT', false, NULL, NOW(), NOW()),
(5, NULL, false, NULL, NOW(), NOW()),

-- cas avec besoin spécifique
(6, 'ENCRYPTED_DYSLEXIA', false, NULL, NOW(), NOW()),
(7, NULL, false, NULL, NOW(), NOW()),

-- données sensibles présentes
(8, 'ENCRYPTED_ASTHMA', false, NULL, NOW(), NOW()),

-- anonymisé (RGPD ✔)
(9, NULL, true, NULL, NOW(), NOW()),

-- supprimé (droit à l'effacement ✔)
(10, NULL, true, NOW(), NOW(), NOW())
    ON CONFLICT (id) DO NOTHING;