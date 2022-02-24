package net.dcess.gestrec.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import net.dcess.gestrec.entity.Invoice;

/**
 *
 * @author Ouiame
 */

@Stateless
@Slf4j
public class InvoiceDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Invoice> loadAllInvoices() {
        return this.entityManager.createQuery("SELECT i FROM Invoice i", Invoice.class).getResultList();

    }

    public void addNewInvoice(Invoice invoice) {

        Invoice newInvoice = new Invoice();
        newInvoice.setCustomer(invoice.getCustomer());
        newInvoice.setAmount(invoice.getAmount());
        newInvoice.setDueDate(invoice.getDueDate());
        newInvoice.setDescription(invoice.getDescription());
        newInvoice.setCreationDate(invoice.getCreationDate());
        this.entityManager.persist(newInvoice);
    }

    public void delete(Invoice invoice) {
        if (entityManager.contains(invoice)) {
            entityManager.remove(invoice);
        } else {
            Invoice managedInvoice = entityManager.find(Invoice.class, invoice.getId());
            if (managedInvoice != null) {
                entityManager.remove(managedInvoice);
            }
        }
    }

    public void update(List<Invoice> invoice) {
        invoice.forEach(entityManager::merge);
    }

}
