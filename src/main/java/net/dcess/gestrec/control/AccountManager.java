package net.dcess.gestrec.control;

import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import net.dcess.gestrec.entity.Account;


@Stateless
public class AccountManager {
    
    @PersistenceContext
    private EntityManager entityManager;

    public List<Account> loadAllAccounts() {
        return this.entityManager.createQuery("SELECT a FROM Account a", Account.class).getResultList();
    }

    public void delete(Account account) {
        if (entityManager.contains(account)) {
            entityManager.remove(account);
        } else {
            Account managedAccount = entityManager.find(Account.class, account.getUsername());
            if (managedAccount != null) {
                entityManager.remove(managedAccount);
            }
        }
    }

    public void addNewAccount(Account account) {

        Account newAccount = new Account();
        newAccount.setUsername(account.getUsername());
        newAccount.setPassword(account.getPassword());
        newAccount.setFirstName(account.getFirstName());
        newAccount.setLastName(account.getLastName());
        newAccount.setEmail(account.getEmail());
       
        this.entityManager.persist(newAccount);
    }

    public void update(List<Account> accounts) {
        accounts.forEach(entityManager::merge);
    }

public List<Account> getUserByUsernameAndPassword(){

Query query=entityManager.createQuery("select a from a where a.username:u and a.password:p");   
query.setParameter("u", this);
query.setParameter("p", this);
return query.getResultList();
    }
}