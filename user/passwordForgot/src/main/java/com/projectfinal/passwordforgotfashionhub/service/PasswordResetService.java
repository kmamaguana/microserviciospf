package com.projectfinal.passwordforgotfashionhub.service;

public interface PasswordResetService {

    void createAndSendResetToken(String email);

}
