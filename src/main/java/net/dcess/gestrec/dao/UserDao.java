/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.dcess.gestrec.dao;

import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import net.dcess.gestrec.entity.User;

/**
 *
 * @author aaytouna
 */

@Stateless
@Slf4j
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> loadAllUsers() {
        log.info("+loadAllUsers");
        log.debug("+tesddt");
        return this.entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public void delete(User user) {
        if (entityManager.contains(user)) {
            entityManager.remove(user);
        } else {
            User managedUser = entityManager.find(User.class, user.getId());
            if (managedUser != null) {
                entityManager.remove(managedUser);
            }
        }
    }

    public void addNewUser(User user) {

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());

        this.entityManager.persist(newUser);
    }

    public void update(List<User> users) {
        users.forEach(entityManager::merge);
    }
}

