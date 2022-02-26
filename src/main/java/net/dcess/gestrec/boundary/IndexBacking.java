/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.dcess.gestrec.boundary;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import lombok.Data;
import net.dcess.gestrec.dao.InvoiceDao;
import net.dcess.gestrec.entity.Invoice;

/**
 *
 * @author aaytouna
 */
@Data
@Named
@ViewScoped
public class IndexBacking implements Serializable {

    private List<Invoice> invoices;
    private LocalDate dueDate = LocalDate.now();
    private LocalDate orangeDate = dueDate.plusDays(7);

    @Inject
    private InvoiceDao invoiceDao;

    @PostConstruct
    public void init() {
        this.invoices = invoiceDao.loadDueInvoices();
    }
    
    public void paid(Invoice invoice) throws IOException {
        invoice.setPaid(Boolean.TRUE);
        invoiceDao.update(invoice);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Update successful"));
    }
}
