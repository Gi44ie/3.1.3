package com.example313.dao;


import com.example313.model.User;
import java.util.List;
import java.util.Optional;

public interface UserDAO {
    User getUser(Long id);
    void addUser(User user, String[] roles);
    void removeUser(Long id);
    void updateUser(User user, String[] roles);
    List<User> getAllUsers();
    User loadUserByUsername(String s);
    Optional<User> findById(Long id);
}