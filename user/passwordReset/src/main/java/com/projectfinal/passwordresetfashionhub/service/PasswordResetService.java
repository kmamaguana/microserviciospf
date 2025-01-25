package com.projectfinal.passwordresetfashionhub.service;

public interface PasswordResetService {

    void resetPassword(String token, String newPassword);

}
