package com.Java.NearBuzz.chat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ChatRequest(
        @NotBlank @Size(max = 2000) String message,
        @Size(max = 12) List<ChatMessage> history) {
}
