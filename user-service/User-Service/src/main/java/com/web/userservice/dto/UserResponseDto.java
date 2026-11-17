package com.web.userservice.dto;

import com.web.userservice.entity.Role;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserResponseDto {

    private UUID userId;
    private String userName;
    private String password;
    private String email;
    private Role role;
    private String githubUsername;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
