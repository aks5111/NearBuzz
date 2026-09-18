package com.Java.NearBuzz.admin.controller;

import com.Java.NearBuzz.common.response.ApiResponse;
import com.Java.NearBuzz.user.dto.response.UserResponse;
import com.Java.NearBuzz.user.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/users")
public class AdminUserController {

    private final UserRepository userRepository;

    public AdminUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> list() {
        List<UserResponse> users = userRepository.findAll().stream()
                .map(UserResponse::from)
                .toList();
        return ApiResponse.ok("Users fetched", users);
    }
}
