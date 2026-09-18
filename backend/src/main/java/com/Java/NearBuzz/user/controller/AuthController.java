package com.Java.NearBuzz.user.controller;

import com.Java.NearBuzz.common.response.ApiResponse;
import com.Java.NearBuzz.user.dto.request.LoginRequest;
import com.Java.NearBuzz.user.dto.response.LoginResponse;
import com.Java.NearBuzz.user.dto.response.UserResponse;
import com.Java.NearBuzz.user.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.ok("Login successful", authService.login(request));
    }

    @GetMapping("/me")
    public ApiResponse<UserResponse> me(@AuthenticationPrincipal UserDetails principal) {
        return ApiResponse.ok("Current user", authService.currentUser(principal.getUsername()));
    }
}
