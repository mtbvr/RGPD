package com.example.rgpd.common.entity.sensitive;

import com.example.rgpd.common.entity.identity.StudentIdentityEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "student_sensitive")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentSensitiveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "allergy")
    private String allergy;

    @Column(name = "student_id")
    private Long studentIdentityId;

    @Column(name = "is_anonymized")
    private Boolean isAnonymized = false;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @Column(name="created_at")
    private Instant createdAt;

    @Column(name="updated_at")
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
