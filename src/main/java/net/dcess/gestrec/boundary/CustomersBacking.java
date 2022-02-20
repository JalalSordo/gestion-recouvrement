package net.dcess.gestrec.boundary;

import net.dcess.gestrec.control.CustomerManager;
import net.dcess.gestrec.entity.Customer;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
@Named
@ViewScoped
public class CustomersBacking implements Serializable {

    private List<Customer> customers;

    private Customer customer = new Customer();

    @Inject
    private CustomerManager customerManager;

    @PostConstruct
    public void init() {
        this.customers = customerManager.loadAllCustomers();
    }

    public void delete(Customer customer) {
        customerManager.delete(customer);
        customers.remove(customer);
    }

    public void add() {
        customerManager.addNewCustomer(customer);
        this.customers = customerManager.loadAllCustomers();
        this.customer = new Customer();
    }

    public void update() {
        customerManager.update(customers);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Update successful"));
    }

}
