package net.dcess.gestrec.boundary;

import net.dcess.gestrec.entity.Invoice;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import net.dcess.gestrec.dao.CustomerDao;
import net.dcess.gestrec.dao.InvoiceDao;
import net.dcess.gestrec.entity.Customer;

/**
 *
 * @author ouiame
 */
@Data
@Named
@ViewScoped
public class InvoicesBacking implements Serializable {

    private List<Invoice> invoices;

    private List<Customer> customers;

    private Invoice invoice = new Invoice();

    private Customer selectedCustomer = null;

    @Inject
    private InvoiceDao invoiceDao;

    @Inject
    private CustomerDao customerDao;

    @PostConstruct
    public void init() {
        this.invoices = invoiceDao.loadAllInvoices();
        this.customers = customerDao.loadAllCustomers();
    }

    public void delete(Invoice invoice) {
        invoiceDao.delete(invoice);
        invoices.remove(invoice);
    }

    public void add() {
        invoice.setCustomer(selectedCustomer);
        invoiceDao.addNewInvoice(invoice);
        this.invoices = invoiceDao.loadAllInvoices();
        this.invoice = new Invoice();
        selectedCustomer = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Invoice Added successfully!"));
    }

    public void update() {
        invoiceDao.update(invoices);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Update successful"));
    }

}
