package com.web.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginRequestDto {

    @NotBlank(message = "Username field should not be blank.")
    private String userName;
    @NotBlank(message = "Password field should not be blank.")
    @Length(min = 8, max = 12)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9@]+$", message = "Password must contain uppercase, lowercase, numbers.")
    private String password;
}
