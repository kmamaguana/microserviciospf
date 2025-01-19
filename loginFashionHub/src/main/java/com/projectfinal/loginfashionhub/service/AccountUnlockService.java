package com.projectfinal.loginfashionhub.service;

public interface AccountUnlockService {

    void createAndSendUnlockToken(String email);

    void unlockAccount(String token);

    void deleteByUser(Integer userId);
}
