package net.dcess.gestrec.dao;

import net.dcess.gestrec.entity.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

@Stateless
@Slf4j
public class CustomerDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Customer> loadAllCustomers() {
        log.info("+loadAllCustomers");
        log.debug("+tesddt");
        return this.entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    public void delete(Customer customer) {
        if (entityManager.contains(customer)) {
            entityManager.remove(customer);
        } else {
            Customer managedCustomer = entityManager.find(Customer.class, customer.getId());
            if (managedCustomer != null) {
                entityManager.remove(managedCustomer);
            }
        }
    }

    public void addNewCustomer(Customer customer) {

        Customer newCustomer = new Customer();
        newCustomer.setDayOfBirth(customer.getDayOfBirth());
        newCustomer.setEmail(customer.getEmail());
        newCustomer.setFirstName(customer.getFirstName());
        newCustomer.setLastName(customer.getLastName());
        newCustomer.setCustomerId(UUID.randomUUID().toString().substring(0, 8));

        this.entityManager.persist(newCustomer);
    }

    public void update(List<Customer> customers) {
        customers.forEach(entityManager::merge);
    }
}
