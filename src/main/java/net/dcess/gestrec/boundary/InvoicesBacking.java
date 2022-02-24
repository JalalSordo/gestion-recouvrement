/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.dcess.gestrec.boundary;

import net.dcess.gestrec.control.InvoiceManager;
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
    private InvoiceManager invoiceManager;

    @PostConstruct
    public void init() {
        this.invoices = invoiceManager.loadAllInvoices();
    }

    public void delete(Invoice invoice) {
        invoiceManager.delete(invoice);
        invoices.remove(invoice);
    }

    public void add() {
        invoiceManager.addNewInvoice(invoice);
        this.invoices = invoiceManager.loadAllInvoices();
        this.invoice = new Invoice();
    }

    public void update() {
        invoiceManager.update(invoices);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Update successful"));
    }

    
}
