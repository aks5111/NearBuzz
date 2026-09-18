package com.Java.NearBuzz.user.dto.response;

public record LoginResponse(String token, String fullName, String email, String role) {
}
