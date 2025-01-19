package com.projectfinal.loginfashionhub.component;

import com.projectfinal.loginfashionhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AccountDeletionScheduler {

    private final UserRepository userRepository;

    @Scheduled(cron = "0 0 0 * * *") // Ejecutar diariamente
    public void deleteScheduledAccounts() {
        LocalDateTime now = LocalDateTime.now();
        userRepository.findAll().stream()
                .filter(user -> user.getScheduledForDeletion() != null && user.getScheduledForDeletion().isBefore(now))
                .forEach(userRepository::delete);
    }
}
