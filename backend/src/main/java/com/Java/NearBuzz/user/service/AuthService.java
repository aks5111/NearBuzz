package com.Java.NearBuzz.user.service;

import com.Java.NearBuzz.user.dto.request.LoginRequest;
import com.Java.NearBuzz.user.dto.response.LoginResponse;
import com.Java.NearBuzz.user.dto.response.UserResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);

    UserResponse currentUser(String email);
}
