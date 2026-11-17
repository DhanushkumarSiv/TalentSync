package com.web.userservice.dto;

import com.web.userservice.entity.Levels;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class SkillRequestDto {


    private UUID skillId;

    private String skillName;

    @NotNull(message = "Select your skill level.")
    private Levels declaredLevel;
}
