package com.Java.NearBuzz.config;

import com.Java.NearBuzz.user.entity.Role;
import com.Java.NearBuzz.user.entity.User;
import com.Java.NearBuzz.user.entity.UserStatus;
import com.Java.NearBuzz.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Ensures exactly one super admin account exists on startup. Credentials
 * come from app.seed.* (env-backed per profile) — see application-dev/prod
 * .properties. Safe to run every boot: no-op once the account exists.
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.seed.super-admin-email}")
    private String superAdminEmail;

    @Value("${app.seed.super-admin-password}")
    private String superAdminPassword;

    public DataSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        String email = superAdminEmail.toLowerCase();
        if (userRepository.existsByEmail(email)) {
            return;
        }

        User superAdmin = User.builder()
                .fullName("Super Admin")
                .email(email)
                .passwordHash(passwordEncoder.encode(superAdminPassword))
                .role(Role.ROLE_SUPER_ADMIN)
                .status(UserStatus.ACTIVE)
                .build();
        userRepository.save(superAdmin);
    }
}
