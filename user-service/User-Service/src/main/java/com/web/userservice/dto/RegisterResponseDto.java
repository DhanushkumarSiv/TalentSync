package com.web.userservice.dto;

import com.web.userservice.entity.Role;
import lombok.Data;
import java.util.UUID;
import java.time.LocalDateTime;

@Data
public class RegisterResponseDto {

    private UUID userId;
    private String userName;
    private String password;
    private String email;
    private Role role;
    private LocalDateTime createdAt;
}
