package com.Java.NearBuzz.chat.service;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The chat endpoint is public (no login required) and calls a paid OpenAI
 * API, so it needs its own abuse guard independent of login throttling —
 * otherwise anyone can run up the API bill by hammering it.
 */
@Service
public class ChatRateLimiter {

    private static final int MAX_MESSAGES = 20;
    private static final Duration WINDOW = Duration.ofMinutes(10);

    private record Window(AtomicInteger count, Instant startedAt) {
    }

    private final ConcurrentHashMap<String, Window> windowsByKey = new ConcurrentHashMap<>();

    public boolean tryConsume(String key) {
        Window window = windowsByKey.compute(key, (k, existing) -> {
            if (existing == null || Duration.between(existing.startedAt(), Instant.now()).compareTo(WINDOW) > 0) {
                return new Window(new AtomicInteger(0), Instant.now());
            }
            return existing;
        });
        return window.count().incrementAndGet() <= MAX_MESSAGES;
    }
}
