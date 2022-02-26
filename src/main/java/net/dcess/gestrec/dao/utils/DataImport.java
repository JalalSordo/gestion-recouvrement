package net.dcess.gestrec.dao.utils;

import net.dcess.gestrec.entity.Customer;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.UUID;
import net.dcess.gestrec.entity.Invoice;
import net.dcess.gestrec.entity.User;

@Startup
@Singleton
public class DataImport {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {

        System.out.println("Storing three initial customers");

        this.entityManager.persist(createCustomer("John", "Doe", "doe@john.de", LocalDate.of(1990, 12, 12)));
        this.entityManager.persist(createCustomer("Max", "Mustermann", "max@gmail.com", LocalDate.of(1985, 4,
                20)));
        Customer customerPhilip = createCustomer("Philip", "Riecks", "philip@riecks.de", LocalDate.of(1995, 9,
                13));
        this.entityManager.persist(customerPhilip);

        this.entityManager.persist(createUser("Jalal", "Sordo", "admin", "admin", "js@live.com"));
        this.entityManager.persist(createInvoice("Bon de commande N10256", customerPhilip, 1000.0, LocalDate.of(2022, 9,
                13)));
        this.entityManager.persist(createInvoice("Bon de commande N10255", customerPhilip, 1000.0, LocalDate.of(2022, 2,
                13)));
        this.entityManager.persist(createInvoice("Bon de commande N10254", customerPhilip, 1000.0, LocalDate.of(2022, 1,
                13)));
        this.entityManager.persist(createInvoice("Bon de commande N10254", customerPhilip, 1000.0, LocalDate.now()));
    }

    private Customer createCustomer(String firstName, String lastName, String email, LocalDate dayOfBirth) {
        Customer result = new Customer();
        result.setFirstName(firstName);
        result.setLastName(lastName);
        result.setEmail(email);
        result.setDayOfBirth(dayOfBirth);
        result.setCustomerId(UUID.randomUUID().toString().substring(0, 8));
        return result;
    }

    private User createUser(String firtName, String lastName, String username, String password, String email) {
        User user = new User();
        user.setFirstName(firtName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        return user;
    }

    private Invoice createInvoice(String description, Customer customer, Double amount, LocalDate dueDate) {
        Invoice invoice = new Invoice();
        invoice.setDescription(description);
        invoice.setCustomer(customer);
        invoice.setAmount(amount);
        invoice.setCreationDate(LocalDate.now());
        invoice.setDueDate(dueDate);
        invoice.setPaid(Boolean.FALSE);
        return invoice;
    }
}
