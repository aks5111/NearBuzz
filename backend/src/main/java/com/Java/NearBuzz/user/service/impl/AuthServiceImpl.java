package com.Java.NearBuzz.user.service.impl;

import com.Java.NearBuzz.common.exception.BadRequestException;
import com.Java.NearBuzz.security.LoginAttemptService;
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

    // Used only to burn roughly the same CPU time as a real bcrypt check when
    // the email doesn't exist, so response latency can't be used to enumerate
    // valid accounts.
    private static final String DUMMY_HASH =
            "$2a$10$7EqJtq98hPqEX7fNZaFWoOa2LT2/tXVYZFf1BSajhZ0F0Xj8Xj8Xu";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final LoginAttemptService loginAttemptService;

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            LoginAttemptService loginAttemptService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.loginAttemptService = loginAttemptService;
    }

    @Override
    @Transactional
    public LoginResponse login(LoginRequest request) {
        String email = request.email().toLowerCase();

        if (loginAttemptService.isLocked(email)) {
            throw new BadRequestException("Too many failed attempts. Try again in 15 minutes.");
        }

        // Look up first, but don't branch on "user missing" vs "wrong password" —
        // both fail through the same BadCredentialsException below, so a caller
        // can't use this endpoint to discover which emails have an account.
        User user = userRepository.findByEmail(email).orElse(null);

        String hashToCheck = user != null ? user.getPasswordHash() : DUMMY_HASH;
        boolean passwordMatches = passwordEncoder.matches(request.password(), hashToCheck) && user != null;

        if (!passwordMatches) {
            loginAttemptService.recordFailure(email);
            throw new BadCredentialsException("Invalid email or password");
        }

        if (user.getStatus() == UserStatus.BLOCKED) {
            // Only reachable once the password is already proven correct,
            // so this doesn't leak account existence to an unauthenticated caller.
            throw new BadRequestException("This account has been blocked");
        }

        loginAttemptService.recordSuccess(email);
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
