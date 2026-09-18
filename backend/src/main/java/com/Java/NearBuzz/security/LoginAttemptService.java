package com.Java.NearBuzz.security;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * In-memory brute-force guard for /auth/login, keyed by email. Good enough
 * for a single-instance deployment; swap for a Redis-backed counter if the
 * app ever runs behind a load balancer with multiple instances.
 */
@Service
public class LoginAttemptService {

    private static final int MAX_ATTEMPTS = 5;
    private static final Duration LOCKOUT_DURATION = Duration.ofMinutes(15);

    private record Attempts(AtomicInteger count, Instant firstFailureAt) {
    }

    private final ConcurrentHashMap<String, Attempts> failuresByKey = new ConcurrentHashMap<>();

    public void recordFailure(String key) {
        failuresByKey.compute(key, (k, existing) -> {
            if (existing == null || Duration.between(existing.firstFailureAt(), Instant.now()).compareTo(LOCKOUT_DURATION) > 0) {
                return new Attempts(new AtomicInteger(1), Instant.now());
            }
            existing.count().incrementAndGet();
            return existing;
        });
    }

    public void recordSuccess(String key) {
        failuresByKey.remove(key);
    }

    public boolean isLocked(String key) {
        Attempts attempts = failuresByKey.get(key);
        if (attempts == null) {
            return false;
        }
        if (Duration.between(attempts.firstFailureAt(), Instant.now()).compareTo(LOCKOUT_DURATION) > 0) {
            failuresByKey.remove(key);
            return false;
        }
        return attempts.count().get() >= MAX_ATTEMPTS;
    }
}
