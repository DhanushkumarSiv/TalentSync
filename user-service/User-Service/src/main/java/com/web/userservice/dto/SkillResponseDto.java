package com.web.userservice.dto;

import com.web.userservice.entity.Levels;
import lombok.Data;

import java.util.UUID;

@Data
public class SkillResponseDto {

    private UUID userSkillId;

    private UUID userId;

    private UUID skillId;

    private String skillName;

    private Levels declaredLevel;

    private Levels verifiedLevel;

    private Boolean verified;

    private Float confidenceScore;
}
