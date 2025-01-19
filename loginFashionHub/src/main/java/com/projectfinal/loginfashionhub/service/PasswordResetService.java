package com.projectfinal.loginfashionhub.service;

public interface PasswordResetService {

    void createAndSendResetToken(String email);

    void resetPassword(String token, String newPassword);

    void deleteByUser(Integer userId);
}
