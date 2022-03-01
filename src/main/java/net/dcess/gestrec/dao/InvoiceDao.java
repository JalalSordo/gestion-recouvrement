package net.dcess.gestrec.dao;

import java.time.LocalDate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import net.dcess.gestrec.entity.Invoice;
import net.dcess.gestrec.entity.User;

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
    
    public List<Invoice> loadDueInvoices() {
        TypedQuery<Invoice> query = entityManager.createQuery(
                "SELECT i FROM Invoice i WHERE i.paid =:paid", Invoice.class);
        return query.setParameter("paid", false).getResultList();
    }

    public void addNewInvoice(Invoice invoice) {

        Invoice newInvoice = new Invoice();
        newInvoice.setCustomer(invoice.getCustomer());
        newInvoice.setAmount(invoice.getAmount());
        newInvoice.setDueDate(invoice.getDueDate());
        newInvoice.setDescription(invoice.getDescription());
        newInvoice.setCreationDate(invoice.getCreationDate());
        newInvoice.setPaid(Boolean.FALSE);
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
    
    public void update(Invoice invoice) {
        entityManager.merge(invoice);
    }
}
