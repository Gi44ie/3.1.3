package com.example313.dao;


import com.example313.model.Role;
import java.util.List;

public interface RoleDAO {
    List<Role> getRoles();
    Role getRoleById(String id);
    Role getRoleByName(String name);
}
