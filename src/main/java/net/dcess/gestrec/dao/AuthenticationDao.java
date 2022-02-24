package net.dcess.gestrec.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import net.dcess.gestrec.entity.User;

/**
 *
 * @author aaytouna
 */
@Stateless
public class AuthenticationDao {

    @PersistenceContext
    private EntityManager entityManager;

    public User getUser(String username, String password) {

        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username =:username and u.password=:password", User.class);
        return query.setParameter("username", username).setParameter("password", password).getResultList()
                .stream().findFirst().orElse(null);

    }

}
