package com.projectfinal.registerfashionhub.config;

import com.projectfinal.registerfashionhub.model.Role;
import com.projectfinal.registerfashionhub.model.User;
import com.projectfinal.registerfashionhub.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

/**
 * Configuration class for initializing PostgreSQL tables with default data.
 */
@Configuration
public class PostgresqlConfig {

    /**
     * Creates a default admin user if not already present.
     *
     * @param userRepository the user repository
     * @param passwordEncoder the password encoder
     * @return a CommandLineRunner to execute the initialization logic
     */
    @Bean
    CommandLineRunner initPostgresTables(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            final String adminUsername = "admin";
            if (userRepository.findByUsername(adminUsername).isPresent()) {
                System.out.println("Admin user already exists.");
                return;
            }

            User adminUser = createAdminUser(passwordEncoder);
            userRepository.save(adminUser);
            System.out.println("Admin user created successfully.");
        };
    }

    private User createAdminUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .nombre("Administrator")
                .apellido("Administrator")
                .email("admin@fashionhub.com")
                .username("admin")
                .password(passwordEncoder.encode("Admin123@"))
                .role(Role.VENDOR)
                .createdAt(LocalDateTime.now())
                .phoneNumber("1234567890")
                .accountLocked(false)
                .enabled(true)
                .build();
    }
}
