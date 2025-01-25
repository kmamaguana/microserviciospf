package com.projectfinal.passwordresetfashionhub.service.impl;

import com.projectfinal.passwordresetfashionhub.model.PasswordResetToken;
import com.projectfinal.passwordresetfashionhub.model.User;
import com.projectfinal.passwordresetfashionhub.repository.PasswordResetTokenRepository;
import com.projectfinal.passwordresetfashionhub.repository.UserRepository;
import com.projectfinal.passwordresetfashionhub.service.PasswordResetService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {

    private final PasswordResetTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String INVALID_OR_EXPIRED_TOKEN = "Token inválido o expirado.";


    @Override
    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_OR_EXPIRED_TOKEN));

        validateToken(resetToken);

        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        markTokenAsUsed(resetToken);
    }

    private void validateToken(PasswordResetToken resetToken) {
        if (resetToken.isUsed() || resetToken.getExpiryDate().before(new Date())) {
            throw new IllegalArgumentException(INVALID_OR_EXPIRED_TOKEN);
        }
    }

    private void markTokenAsUsed(PasswordResetToken token) {
        token.setUsed(true);
        tokenRepository.save(token);
    }
}
