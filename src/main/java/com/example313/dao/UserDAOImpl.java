package com.example313.dao;

import com.example313.model.Role;
import com.example313.model.User;
import com.example313.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private RoleService roleService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public User getUser(Long id) {
        return entityManager.find(User.class, id);
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
        entityManager.persist(user);
    }

    @Override
    public void removeUser(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public void updateUser(User user, String[] roles) {
        if(!(roles == null)) {
            Set<Role> roleSet = new HashSet<>();
            for (String role : roles) {
                roleSet.add(roleService.getRoleByName(role));
            }
            user.setRoles(roleSet);
        }
        entityManager.merge(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select distinct user from User user join fetch user.roles roles").getResultList();
    }

    @Override
    public User loadUserByUsername(String s) {
        return (User) entityManager.createQuery("select distinct user from User user join fetch user.roles roles where user.email=:email")
                .setParameter("email", s).getSingleResult();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.of((User) entityManager.createQuery("select distinct user from User user join fetch user.roles roles where user.id=:id").getSingleResult());
    }
}