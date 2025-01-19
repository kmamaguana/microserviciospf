package com.projectfinal.loginfashionhub.service.impl;

import com.projectfinal.loginfashionhub.model.AccountUnlockToken;
import com.projectfinal.loginfashionhub.model.User;
import com.projectfinal.loginfashionhub.repository.AccountUnlockTokenRepository;
import com.projectfinal.loginfashionhub.repository.UserRepository;
import com.projectfinal.loginfashionhub.service.AccountUnlockService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountUnlockServiceImpl implements AccountUnlockService {

    private final AccountUnlockTokenRepository unlockTokenRepository;
    private final UserRepository userDao;
    private final JavaMailSender mailSender;

    @Value("${url.unlock-account-client1}")
    private String unlockUrlClient;

    @Value("${spring.mail.username}")
    private String email;

    @Override
    public void createAndSendUnlockToken(String email) {
        User user = userDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (!user.isAccountLocked()) {
            throw new IllegalStateException("La cuenta no está bloqueada.");
        }

        String token = UUID.randomUUID().toString(); // Genera un token aleatorio

        // Crea un nuevo token de desbloqueo y lo almacena
        AccountUnlockToken unlockToken = new AccountUnlockToken();
        unlockToken.setToken(token);
        unlockToken.setUser(user);
        unlockToken.setExpiryDate(new Date(System.currentTimeMillis() + 300000)); // Expira en 5 minutos
        unlockToken.setUsed(false);
        unlockTokenRepository.save(unlockToken);

        // Intenta enviar el correo con el token
        try {
            sendUnlockEmail(user.getEmail(), token);
        } catch (MessagingException e) {
            throw new MailSendException("Error enviando el correo electrónico con el token de desbloqueo.");
        }
    }

    private void sendUnlockEmail(String to, String token) throws MessagingException {
        String unlockLink = unlockUrlClient + token;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(email);
        helper.setTo(to);
        helper.setSubject("Desbloqueo de Cuenta");

        // Cuerpo del correo en formato HTML
        String htmlContent = "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: auto; padding: 20px; border: 1px solid #ccc; border-radius: 10px; background-color: #f9f9f9; text-align: center;'>"
                + "<h2 style='color: #2c3e50;'>Desbloqueo de Cuenta</h2>"
                + "<p style='color: #34495e; font-size: 16px;'>Has solicitado desbloquear tu cuenta en <strong>TuAplicacion</strong>.</p>"
                + "<p style='margin: 20px 0;'>"
                + "<a href='" + unlockLink + "' style='background-color: #3498db; color: white; padding: 12px 24px; text-decoration: none; border-radius: 5px; font-size: 18px;'>Desbloquear Cuenta</a>"
                + "</p>"
                + "<p style='color: #7f8c8d;'>Si no has solicitado este desbloqueo, por favor ignora este mensaje o contacta con nuestro equipo de soporte.</p>"
                + "</div>";

        helper.setText(htmlContent, true); // Establece el contenido HTML del mensaje

        mailSender.send(message); // Envía el correo
    }

    @Override
    public void unlockAccount(String token) {
        AccountUnlockToken unlockToken = unlockTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Token inválido o expirado"));

        if (unlockToken.isUsed() || new Date().after(unlockToken.getExpiryDate())) {
            throw new IllegalArgumentException("Token inválido o expirado");
        }

        User user = unlockToken.getUser();
        user.setAccountLocked(false); // Desbloquea la cuenta del usuario
        user.setFailedLoginAttempts(0); // Restablece los intentos fallidos
        userDao.save(user); // Guarda los cambios del usuario

        unlockToken.setUsed(true); // Marca el token como usado
        unlockTokenRepository.save(unlockToken); // Guarda el token actualizado
    }

    @Override
    @Transactional
    public void deleteByUser(Integer userId) {
        unlockTokenRepository.deleteByUserId(userId); // Elimina los tokens asociados al usuario
    }
}
