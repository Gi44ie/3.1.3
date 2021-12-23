package com.example313.service;


import com.example313.dao.UserDAO;
import com.example313.model.Role;
import com.example313.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleService roleService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public User getUser(Long id) {
        return userDAO.getUser(id);
    }

    @Override
    public void addUser(User user, String[] roles) {
        user.setPassword(encoder.encode(user.getPassword()));
        if (!(roles == null)) {
            Set<Role> roleSet = new HashSet<>();
            for (String role : roles) {
                roleSet.add(roleService.getRoleByName(role));
            }
            user.setRoles(roleSet);
        }
        userDAO.addUser(user);
    }

    @Override
    public void removeUser(Long id) {
        userDAO.removeUser(id);
    }

    @Override
    public void updateUser(User user, String[] roles) {
        user.setPassword(encoder.encode(user.getPassword()));
        if(!(roles == null)) {
            Set<Role> roleSet = new HashSet<>();
            for (String role : roles) {
                roleSet.add(roleService.getRoleByName(role));
            }
            user.setRoles(roleSet);
        }
        userDAO.updateUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User loadUserByUsername(String s) {
        return userDAO.loadUserByUsername(s);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userDAO.findById(id);
    }
}