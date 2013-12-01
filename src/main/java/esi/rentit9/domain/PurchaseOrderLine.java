package esi.rentit9.domain;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class PurchaseOrderLine {

    /**
     */
    @ManyToOne
    private PurchaseOrder purchaseOrder;

    /**
     */
    @ManyToOne
    private Plant plant;

}
