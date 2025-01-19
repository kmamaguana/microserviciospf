package com.projectfinal.loginfashionhub.config;

import com.projectfinal.loginfashionhub.model.Role;
import com.projectfinal.loginfashionhub.model.User;
import com.projectfinal.loginfashionhub.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

@Configuration
public class PostgresqlConfig {

    @Bean
    CommandLineRunner initPostgresTables(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            final String defaultUsername = "admin";
            if (userRepository.findByUsername(defaultUsername).isPresent()) {
                System.out.println("Administrator already exists.");
                return;
            }

            User adminUser = createAdminUser(passwordEncoder);
            userRepository.save(adminUser);
            System.out.println("Administrator created successfully.");
        };
    }

    private User createAdminUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .nombre("Administrador")
                .apellido("Administrador")
                .email("admin@facso.com")
                .username("admin")
                .password(passwordEncoder.encode("Admin123@"))
                .role(Role.VENDOR)
                .createdAt(LocalDateTime.now())
                .phoneNumber("0978624884")
                .accountLocked(false)
                .enabled(true)
                .build();
    }
}
