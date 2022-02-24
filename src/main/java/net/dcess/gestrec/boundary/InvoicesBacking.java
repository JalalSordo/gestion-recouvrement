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
import net.dcess.gestrec.dao.InvoiceDao;

/**
 *
 * @author ouiame
 */
@Data
@Named
@ViewScoped
public class InvoicesBacking implements Serializable {

    private List<Invoice> invoices;

    private Invoice invoice = new Invoice();

    @Inject
    private InvoiceDao invoiceDao;

    @PostConstruct
    public void init() {
        this.invoices = invoiceDao.loadAllInvoices();
    }

    public void delete(Invoice invoice) {
        invoiceDao.delete(invoice);
        invoices.remove(invoice);
    }

    public void add() {
        invoiceDao.addNewInvoice(invoice);
        this.invoices = invoiceDao.loadAllInvoices();
        this.invoice = new Invoice();
    }

    public void update() {
        invoiceDao.update(invoices);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Update successful"));
    }

}
