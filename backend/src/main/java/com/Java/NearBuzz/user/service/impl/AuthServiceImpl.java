package com.Java.NearBuzz.user.service.impl;

import com.Java.NearBuzz.common.exception.BadRequestException;
import com.Java.NearBuzz.security.jwt.JwtService;
import com.Java.NearBuzz.user.dto.request.LoginRequest;
import com.Java.NearBuzz.user.dto.response.LoginResponse;
import com.Java.NearBuzz.user.dto.response.UserResponse;
import com.Java.NearBuzz.user.entity.User;
import com.Java.NearBuzz.user.entity.UserStatus;
import com.Java.NearBuzz.user.repository.UserRepository;
import com.Java.NearBuzz.user.service.AuthService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    @Transactional
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email().toLowerCase())
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));

        if (user.getStatus() == UserStatus.BLOCKED) {
            throw new BadRequestException("This account has been blocked");
        }

        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        user.setLastLoginAt(LocalDateTime.now());
        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return new LoginResponse(token, user.getFullName(), user.getEmail(), user.getRole().name());
    }

    @Override
    public UserResponse currentUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("User not found"));
        return UserResponse.from(user);
    }
}
