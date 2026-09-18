package com.Java.NearBuzz.geo.service;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Places/Directions are public, unauthenticated, and call paid Google APIs
 * once a key is configured — same abuse-guard shape as ChatRateLimiter.
 */
@Service
public class GeoRateLimiter {

    private static final int MAX_REQUESTS = 30;
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
        return window.count().incrementAndGet() <= MAX_REQUESTS;
    }
}
