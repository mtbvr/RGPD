-- =========================
-- STUDENT_IDENTITY
-- =========================
INSERT INTO student_identity (id, name, firstname, email, phone_number, created_at, updated_at) VALUES
                                                                                                    (1, 'Martin', 'Alice', 'alice@test.com', 0600000001, NOW(), NOW()),
                                                                                                    (2, 'Durand', 'Bob', 'bob@test.com', 0600000002, NOW(), NOW()),
                                                                                                    (3, 'Dupont', 'Charlie', 'charlie@test.com', 0600000003, NOW(), NOW()),
                                                                                                    (4, 'Leroy', 'David', 'david@test.com', 0600000004, NOW(), NOW()),
                                                                                                    (5, 'Moreau', 'Emma', 'emma@test.com', 0600000005, NOW(), NOW()),
                                                                                                    (6, 'Simon', 'Lucas', 'lucas@test.com', 0600000006, NOW(), NOW()),
                                                                                                    (7, 'Laurent', 'Lina', 'lina@test.com', 0600000007, NOW(), NOW()),
                                                                                                    (8, 'Michel', 'Hugo', 'hugo@test.com', 0600000008, NOW(), NOW()),
                                                                                                    (9, 'Garcia', 'Jade', 'jade@test.com', 0600000009, NOW(), NOW()),
                                                                                                    (10, 'Roux', 'Leo', 'leo@test.com', 0600000010, NOW(), NOW())
ON CONFLICT (id) DO NOTHING;

-- =========================
-- STUDENT_PARENT
-- =========================
INSERT INTO student_parent (id, name, email, phone_number, student_id, created_at, updated_at) VALUES
                                                                                                   (1, 'Parent Martin', 'parent1@test.com', 0611111111, 1, NOW(), NOW()),
                                                                                                   (2, 'Parent Durand', 'parent2@test.com', 0611111112, 2, NOW(), NOW()),
                                                                                                   (3, 'Parent Dupont', 'parent3@test.com', 0611111113, 3, NOW(), NOW()),
                                                                                                   (4, 'Parent Leroy', 'parent4@test.com', 0611111114, 4, NOW(), NOW()),
                                                                                                   (5, 'Parent Moreau', 'parent5@test.com', 0611111115, 5, NOW(), NOW())
    ON CONFLICT (id) DO NOTHING;

-- =========================
-- STUDENT_SCHOOL
-- =========================
INSERT INTO student_school (id, notes, appreciations, options, student_id, created_at, updated_at) VALUES
                                                                                                       (1, '15/20', 'Good student', 'Math', 1, NOW(), NOW()),
                                                                                                       (2, '12/20', 'Average', 'History', 2, NOW(), NOW()),
                                                                                                       (3, '18/20', 'Excellent', 'Science', 3, NOW(), NOW()),
                                                                                                       (4, '10/20', 'Needs work', 'Sports', 4, NOW(), NOW()),
                                                                                                       (5, '16/20', 'Very good', 'Math', 5, NOW(), NOW())
    ON CONFLICT (id) DO NOTHING;

-- =========================
-- STUDENT_SCHOOL_LIFE
-- =========================
INSERT INTO student_school_life (id, eating_habit, student_id, created_at, updated_at) VALUES
                                                                                           (1, 'Vegetarian', 1, NOW(), NOW()),
                                                                                           (2, 'Standard', 2, NOW(), NOW()),
                                                                                           (3, 'Halal', 3, NOW(), NOW()),
                                                                                           (4, 'No pork', 4, NOW(), NOW()),
                                                                                           (5, 'Standard', 5, NOW(), NOW())
    ON CONFLICT (id) DO NOTHING;