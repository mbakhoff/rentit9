package esi.rentit9.domain;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;
import java.util.List;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class PurchaseOrder {

	public static final String QUERY_ORDER_LINES =
			"SELECT line FROM PurchaseOrderLine AS line WHERE line.purchaseOrder = :order";

	/**
     */
    @ManyToOne
    private BuildIt buildit;

    /**
     */
    private OrderStatus status;

    /**
     */
    private String siteAddress;

	public List<PurchaseOrderLine> getLines() {
		TypedQuery<PurchaseOrderLine> query = entityManager().createQuery(QUERY_ORDER_LINES, PurchaseOrderLine.class);
		query.setParameter("order", this);
		return query.getResultList();
	}

}
