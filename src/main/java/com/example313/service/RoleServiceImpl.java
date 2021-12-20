package com.example313.service;

import com.example313.dao.RoleDAO;
import com.example313.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Transactional
    @Override
    public List<Role> getRoles() {
        return roleDAO.getRoles();
    }

    @Transactional
    @Override
    public Role getRoleById(String id) {
        return roleDAO.getRoleById(id);
    }

    @Transactional
    @Override
    public Role getRoleByName(String name) {
        return roleDAO.getRoleByName(name);
    }
}