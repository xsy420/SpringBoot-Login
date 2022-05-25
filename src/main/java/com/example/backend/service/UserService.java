package com.example.backend.service;

import com.example.backend.entity.User;

@SuppressWarnings("UnusedReturnValue")
public interface UserService {
    User insert(User user);

    User findUserByUsername(String username);

    User updateUser(User user);

    int deleteUserByUsername(String username);
}
