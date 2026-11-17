package com.web.userservice.dto;

import lombok.Data;

@Data
public class UpdateUserRequestDto {

    private String userName;
    private String githubUsername;
}
