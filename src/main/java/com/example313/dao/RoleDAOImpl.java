package com.example313.dao;

import com.example313.model.Role;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class RoleDAOImpl implements RoleDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getRoles() {
        return  entityManager.createQuery("select r from Role r").getResultList();
    }

    @Override
    public Role getRoleById(String id) {
        return entityManager.find(Role.class, Long.valueOf(id));
    }

    @Override
    public Role getRoleByName(String name) {
        return (Role) entityManager.createQuery("select role from Role role where role.role=:role")
                .setParameter("role", name).getSingleResult();
    }
}