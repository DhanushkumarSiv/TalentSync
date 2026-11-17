package com.web.userservice.controller;

import com.web.userservice.dto.*;
import com.web.userservice.entity.Users;
import com.web.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> registerUser(@Valid @RequestBody RegisterRequestDto requestDto) {
        return ResponseEntity.ok(userService.registerUser(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@Valid @RequestBody LoginRequestDto requestDto) {
        return ResponseEntity.ok(userService.verifyUser(requestDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.getUserDetails(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUserById(@PathVariable UUID userId, @RequestBody UpdateUserRequestDto requestDto) {
        return ResponseEntity.ok(userService.updateUserDetails(userId, requestDto));
    }

    @PostMapping("/{userId}/skills")
    public ResponseEntity<SkillResponseDto> addSkill(@PathVariable UUID userId, @RequestBody SkillRequestDto requestDto) {
        return ResponseEntity.ok(userService.addSkillToUser(userId, requestDto));
    }

}
