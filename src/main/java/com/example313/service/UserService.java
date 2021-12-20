package com.example313.service;

import com.example313.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User getUser(Long id);
    void addUser(User user, String[] roles);
    void removeUser(Long id);
    void updateUser(User user, String[] roles);
    List<User> getAllUsers();
    User loadUserByUsername(String s);
    Optional<User> findById(Long id);
}
