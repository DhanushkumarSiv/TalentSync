package com.web.userservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Table(name = "userSkill")
@Entity
public class UserSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userSkillId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users user;

    @Column(name = "skill_id", nullable = false)
    private UUID skillId;

    @Column(name = "skill_name", nullable = false)
    private String skillName;

    @Column(name = "declared_level")
    @Enumerated(EnumType.STRING)
    private Levels declaredLevel;

    @Column(name = "verified_level")
    @Enumerated(EnumType.STRING)
    private Levels verifiedLevel;

    private Boolean verified = false;

    @Column(name = "confidence_score")
    private Float confidenceScore;
}
