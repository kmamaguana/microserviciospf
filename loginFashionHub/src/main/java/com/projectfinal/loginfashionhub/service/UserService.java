package com.projectfinal.loginfashionhub.service;

import com.projectfinal.loginfashionhub.request.PasswordChangeRequest;
import com.projectfinal.loginfashionhub.request.RegisterAdminRequest;
import com.projectfinal.loginfashionhub.request.UserUpdateRequest;
import com.projectfinal.loginfashionhub.response.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void registerUserByAdmin(RegisterAdminRequest request);

    List<UserResponse> getAllUsers();

    Optional<UserResponse> getUserById(Long id);

    void updateUser(Long id, UserUpdateRequest updateRequest);

    void deleteUser(Long id);

    void updateUserInfo(UserUpdateRequest updateRequest);

    void initiateAccountDeletion(String username);

    void cancelAccountDeletion(String username);

    void changePassword(String username, PasswordChangeRequest passwordChangeRequest);

    Optional<UserResponse> getUserByUsername(String username);
}
