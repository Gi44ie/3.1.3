package com.example313.service;


import com.example313.model.Role;
import java.util.List;

public interface RoleService {
    List<Role> getRoles();
    Role getRoleById(String id);
    Role getRoleByName(String name);
}
