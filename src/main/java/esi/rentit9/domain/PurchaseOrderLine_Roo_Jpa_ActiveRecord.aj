// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package esi.rentit9.domain;

import esi.rentit9.domain.PurchaseOrderLine;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect PurchaseOrderLine_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager PurchaseOrderLine.entityManager;
    
    public static final EntityManager PurchaseOrderLine.entityManager() {
        EntityManager em = new PurchaseOrderLine().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long PurchaseOrderLine.countPurchaseOrderLines() {
        return entityManager().createQuery("SELECT COUNT(o) FROM PurchaseOrderLine o", Long.class).getSingleResult();
    }
    
    public static List<PurchaseOrderLine> PurchaseOrderLine.findAllPurchaseOrderLines() {
        return entityManager().createQuery("SELECT o FROM PurchaseOrderLine o", PurchaseOrderLine.class).getResultList();
    }
    
    public static PurchaseOrderLine PurchaseOrderLine.findPurchaseOrderLine(Long id) {
        if (id == null) return null;
        return entityManager().find(PurchaseOrderLine.class, id);
    }
    
    public static List<PurchaseOrderLine> PurchaseOrderLine.findPurchaseOrderLineEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM PurchaseOrderLine o", PurchaseOrderLine.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void PurchaseOrderLine.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void PurchaseOrderLine.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            PurchaseOrderLine attached = PurchaseOrderLine.findPurchaseOrderLine(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void PurchaseOrderLine.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void PurchaseOrderLine.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public PurchaseOrderLine PurchaseOrderLine.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        PurchaseOrderLine merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}