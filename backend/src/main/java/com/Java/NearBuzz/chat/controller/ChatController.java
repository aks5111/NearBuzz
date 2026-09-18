package com.Java.NearBuzz.chat.controller;

import com.Java.NearBuzz.chat.dto.ChatRequest;
import com.Java.NearBuzz.chat.dto.ChatResponse;
import com.Java.NearBuzz.chat.service.ChatRateLimiter;
import com.Java.NearBuzz.chat.service.ChatService;
import com.Java.NearBuzz.common.exception.BadRequestException;
import com.Java.NearBuzz.common.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final ChatService chatService;
    private final ChatRateLimiter rateLimiter;

    public ChatController(ChatService chatService, ChatRateLimiter rateLimiter) {
        this.chatService = chatService;
        this.rateLimiter = rateLimiter;
    }

    @PostMapping
    public ApiResponse<ChatResponse> chat(@Valid @RequestBody ChatRequest request, HttpServletRequest httpRequest) {
        String clientKey = clientKey(httpRequest);
        if (!rateLimiter.tryConsume(clientKey)) {
            throw new BadRequestException("You're sending messages too quickly. Please wait a bit.");
        }
        return ApiResponse.ok("Reply", new ChatResponse(chatService.reply(request)));
    }

    private String clientKey(HttpServletRequest request) {
        String forwardedFor = request.getHeader("X-Forwarded-For");
        return forwardedFor != null ? forwardedFor.split(",")[0].trim() : request.getRemoteAddr();
    }
}
