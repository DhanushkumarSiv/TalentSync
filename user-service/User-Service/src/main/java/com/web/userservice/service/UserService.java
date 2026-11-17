package com.web.userservice.service;

import com.web.userservice.config.SecurityConfiguration;
import com.web.userservice.dto.*;
import com.web.userservice.entity.UserSkill;
import com.web.userservice.entity.Users;
import com.web.userservice.exception.UserAlreadyExistException;
import com.web.userservice.exception.UserNotFoundException;
import com.web.userservice.mapper.EntityToDto;
import com.web.userservice.repository.UserRepository;
import com.web.userservice.repository.UserSkillRepository;
import io.jsonwebtoken.JwtException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserSkillRepository userSkillRepository;
    private final EntityToDto entityToDto;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegisterResponseDto registerUser(RegisterRequestDto registerRequestDto) {

        if(userRepository.existsByEmail(registerRequestDto.getEmail())) {
            throw new UserAlreadyExistException("User with email already exists");
        }
        Users user = new Users();
        user.setUserName(registerRequestDto.getUserName());
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        user.setEmail(registerRequestDto.getEmail());
        user.setRole(registerRequestDto.getRole());
        userRepository.save(user);
        return entityToDto.toRegisterDto(user);
    }

    public List<UserResponseDto> getAllUsers() {

        List<Users> users = userRepository.findAll();
        return users.stream()
                .map(entityToDto::toUserDto)
                .toList();
    }

    public LoginResponseDto verifyUser(LoginRequestDto requestDto) {

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getUserName(), requestDto.getPassword()));

        LoginResponseDto response = new LoginResponseDto();
        try {
            if (authentication.isAuthenticated()) {
                response.setJwtToken(jwtService.generateToken(requestDto.getUserName()));
            }
            return response;
        }
        catch (Exception e) {
            throw new JwtException("JWT key generation failed");
        }
    }

    public UserResponseDto getUserDetails(UUID userId) {

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return entityToDto.toUserDto(user);
    }

    public UserResponseDto updateUserDetails(UUID userId, UpdateUserRequestDto request) {

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setUserName(request.getUserName());
        user.setGithubUsername(request.getGithubUsername());
        userRepository.save(user);
        return entityToDto.toUserDto(user);
    }

    public SkillResponseDto addSkillToUser(UUID userId, SkillRequestDto requestDto) {

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        UserSkill userSkill =  new UserSkill();
        userSkill.setUser(user);
        userSkill.setSkillId(requestDto.getSkillId());
        userSkill.setDeclaredLevel(requestDto.getDeclaredLevel());
        userSkillRepository.save(userSkill);
        return entityToDto.toSkillDto(userSkill);
    }
}
