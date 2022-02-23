/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.dcess.gestrec.control;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.dcess.gestrec.entity.Account;

/**
 *
 * @author aaytouna
 */
@Stateless
public class LoginManager {
    
    @PersistenceContext
    private EntityManager entityManager;

    public Account GetAccount(String username, String password) {
        String query = "SELECT c FROM Account a where username = " + username + " and password = " + password;
        return this.entityManager.createQuery(query, Account.class).getSingleResult();
    }
}
