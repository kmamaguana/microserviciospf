package com.projectfinal.loginfashionhub.service.impl;

import com.projectfinal.loginfashionhub.model.PasswordResetToken;
import com.projectfinal.loginfashionhub.model.User;
import com.projectfinal.loginfashionhub.repository.PasswordResetTokenRepository;
import com.projectfinal.loginfashionhub.repository.UserRepository;
import com.projectfinal.loginfashionhub.service.PasswordResetService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {

    private final PasswordResetTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final JavaMailSender mailSender;
    private final PasswordEncoder passwordEncoder;

    @Value("${url.reset-password-client1}")
    private String resetPasswordUrl;

    @Value("${spring.mail.username}")
    private String senderEmail;

    private static final int TOKEN_EXPIRATION_MINUTES = 5;
    private static final String EMAIL_SUBJECT = "Restablecer Contraseña";
    private static final String INVALID_OR_EXPIRED_TOKEN = "Token inválido o expirado.";

    @Override
    public void createAndSendResetToken(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado para el correo: " + email));

        PasswordResetToken resetToken = createTokenForUser(user);
        tokenRepository.save(resetToken);

        try {
            sendResetEmail(email, resetToken.getToken());
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo de restablecimiento.", e);
        }
    }

    private PasswordResetToken createTokenForUser(User user) {
        String token = UUID.randomUUID().toString();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, TOKEN_EXPIRATION_MINUTES);

        return PasswordResetToken.builder()
                .token(token)
                .user(user)
                .expiryDate(cal.getTime())
                .used(false)
                .build();
    }

    private void sendResetEmail(String to, String token) throws MessagingException {
        String resetLink = resetPasswordUrl + token;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(senderEmail);
        helper.setTo(to);
        helper.setSubject(EMAIL_SUBJECT);

        String htmlContent = generateEmailContent(resetLink);
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }

    private String generateEmailContent(String resetLink) {
        return "<div style='font-family: Arial, sans-serif;'>"
                + "<h2>Restablecer tu contraseña</h2>"
                + "<p>Haz clic en el siguiente enlace para restablecer tu contraseña:</p>"
                + "<a href='" + resetLink + "'>Restablecer Contraseña</a>"
                + "<p>Si no solicitaste este cambio, ignora este correo.</p>"
                + "</div>";
    }

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

    @Override
    @Transactional
    public void deleteByUser(Integer userId) {
        tokenRepository.deleteByUserId(userId);
    }
}
