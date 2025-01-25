package com.projectfinal.passwordforgotfashionhub.service.impl;

import com.projectfinal.passwordforgotfashionhub.model.PasswordResetToken;
import com.projectfinal.passwordforgotfashionhub.model.User;
import com.projectfinal.passwordforgotfashionhub.repository.PasswordResetTokenRepository;
import com.projectfinal.passwordforgotfashionhub.repository.UserRepository;
import com.projectfinal.passwordforgotfashionhub.service.PasswordResetService;
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

}
