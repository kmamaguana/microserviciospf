package com.projectfinal.loginfashionhub.service.impl;

import com.projectfinal.loginfashionhub.model.Role;
import com.projectfinal.loginfashionhub.model.User;
import com.projectfinal.loginfashionhub.repository.UserRepository;
import com.projectfinal.loginfashionhub.request.PasswordChangeRequest;
import com.projectfinal.loginfashionhub.request.RegisterAdminRequest;
import com.projectfinal.loginfashionhub.request.UserUpdateRequest;
import com.projectfinal.loginfashionhub.response.UserResponse;
import com.projectfinal.loginfashionhub.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String email;

    @Override
    public void registerUserByAdmin(RegisterAdminRequest request) {
        validateAdminRequest(request);

        String username = extractUsernameFromEmail(request.getEmail());
        String tempPassword = generateTemporaryPassword();

        User user = buildUser(request, username, tempPassword);
        userRepository.save(user);

        sendPasswordEmail(user.getEmail(), username, tempPassword);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserResponse> getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::mapToUserResponse);
    }

    @Override
    public void updateUser(Long id, UserUpdateRequest updateRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con el id: " + id));

        updateFields(user, updateRequest);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id)
                .ifPresentOrElse(userRepository::delete,
                        () -> { throw new IllegalArgumentException("Usuario no encontrado con el id: " + id); });
    }

    @Override
    public void updateUserInfo(UserUpdateRequest updateRequest) {
        User user = userRepository.findByUsername(updateRequest.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + updateRequest.getUsername()));

        updateFields(user, updateRequest);
        userRepository.save(user);
    }

    @Override
    public void initiateAccountDeletion(String username) {
        updateUserDeletionSchedule(username, LocalDateTime.now().plusDays(31));
    }

    @Override
    public void cancelAccountDeletion(String username) {
        updateUserDeletionSchedule(username, null);
    }

    @Override
    public void changePassword(String username, PasswordChangeRequest passwordChangeRequest) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + username));

        validatePassword(passwordChangeRequest, user);

        user.setPassword(passwordEncoder.encode(passwordChangeRequest.getNewPassword()));
        user.setPasswordChanged(true);
        userRepository.save(user);
    }

    @Override
    public Optional<UserResponse> getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(this::mapToUserResponse);
    }

    // Helper Methods

    private void validateAdminRequest(RegisterAdminRequest request) {
        if (!request.getEmail().endsWith("@uce.edu.ec")) {
            throw new IllegalArgumentException("El correo debe tener el dominio @uce.edu.ec.");
        }
        if (Role.valueOf(request.getRole().toUpperCase()) == null) {
            throw new IllegalArgumentException("El rol especificado no es válido.");
        }
    }

    private String extractUsernameFromEmail(String email) {
        return email.split("@")[0];
    }

    private String generateTemporaryPassword() {
        return UUID.randomUUID().toString().substring(0, 12);
    }

    private User buildUser(RegisterAdminRequest request, String username, String tempPassword) {
        return User.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .email(request.getEmail())
                .username(username)
                .password(passwordEncoder.encode(tempPassword))
                .role(Role.valueOf(request.getRole().toUpperCase()))
                .accountLocked(false)
                .enabled(true)
                .failedLoginAttempts(0)
                .passwordChanged(false)
                .createdAt(LocalDateTime.now())
                .build();
    }

    private void sendPasswordEmail(String to, String username, String tempPassword) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(email);
            helper.setTo(to);
            helper.setSubject("Credenciales de Acceso - Tu Aplicación");
            helper.setText(buildEmailContent(username, tempPassword), true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new IllegalStateException("Error al enviar correo de contraseña temporal.", e);
        }
    }

    private String buildEmailContent(String username, String tempPassword) {
        return """
            <div style='font-family: Arial, sans-serif; max-width: 600px; margin: auto;'>
                <h2>Bienvenido a TuAplicación</h2>
                <p>Se ha creado tu cuenta. Tus credenciales:</p>
                <ul>
                    <li>Usuario: %s</li>
                    <li>Contraseña Temporal: %s</li>
                </ul>
                <p>Recomendamos cambiar tu contraseña al iniciar sesión.</p>
            </div>
        """.formatted(username, tempPassword);
    }

    private void updateUserDeletionSchedule(String username, LocalDateTime deletionDate) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + username));

        user.setScheduledForDeletion(deletionDate);
        userRepository.save(user);
    }

    private void validatePassword(PasswordChangeRequest request, User user) {
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("La contraseña actual no es válida.");
        }
    }

    private void updateFields(User user, UserUpdateRequest updateRequest) {
        if (updateRequest.getNombre() != null) user.setNombre(updateRequest.getNombre());
        if (updateRequest.getApellido() != null) user.setApellido(updateRequest.getApellido());
        if (updateRequest.getEmail() != null) user.setEmail(updateRequest.getEmail());
    }

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .nombre(user.getNombre())
                .apellido(user.getApellido())
                .email(user.getEmail())
                .username(user.getUsername())
                .role(user.getRole().toString())
                .build();
    }
}
