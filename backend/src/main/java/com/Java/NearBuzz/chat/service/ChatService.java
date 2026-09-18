package com.Java.NearBuzz.chat.service;

import com.Java.NearBuzz.chat.dto.ChatMessage;
import com.Java.NearBuzz.chat.dto.ChatRequest;
import com.Java.NearBuzz.common.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ChatService {

    private static final Logger log = LoggerFactory.getLogger(ChatService.class);

    private static final String SYSTEM_PROMPT = """
            You are the NearBuzz shopping and activities assistant. NearBuzz lists
            local activities (travel, fitness, calisthenics, party places, meeting
            new friends nearby, sports, event bookings) and a shopping catalog
            (grocery, mall, clothes, sports, electronics, home & living, beauty).
            Help visitors find things to do or buy, answer questions about how the
            site works, and keep replies short and friendly. If you don't know
            something specific about current listings, say so instead of making it up.
            """;

    private final RestClient restClient;
    private final String apiKey;
    private final String model;

    public ChatService(
            @Value("${openai.api-key:}") String apiKey,
            @Value("${openai.model:gpt-4o-mini}") String model) {
        this.apiKey = apiKey;
        this.model = model;
        this.restClient = RestClient.builder().baseUrl("https://api.openai.com/v1").build();
    }

    public String reply(ChatRequest request) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new BadRequestException("Chat isn't configured yet. Please try again later.");
        }

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "system", "content", SYSTEM_PROMPT));
        if (request.history() != null) {
            for (ChatMessage msg : request.history()) {
                messages.add(Map.of("role", msg.role(), "content", msg.content()));
            }
        }
        messages.add(Map.of("role", "user", "content", request.message()));

        try {
            Map<?, ?> response = restClient.post()
                    .uri("/chat/completions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Bearer " + apiKey)
                    .body(Map.of("model", model, "messages", messages, "max_tokens", 400))
                    .retrieve()
                    .body(Map.class);

            List<?> choices = (List<?>) response.get("choices");
            Map<?, ?> firstChoice = (Map<?, ?>) choices.get(0);
            Map<?, ?> messageObj = (Map<?, ?>) firstChoice.get("message");
            return (String) messageObj.get("content");
        } catch (Exception ex) {
            log.error("OpenAI chat request failed", ex);
            throw new BadRequestException("Chat is temporarily unavailable. Please try again.");
        }
    }
}
