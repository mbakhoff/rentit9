package esi.rentit9.rest;

import esi.rentit9.domain.OrderStatus;
import esi.rentit9.rest.util.ExtendedResourceSupport;
import org.springframework.roo.addon.javabean.RooJavaBean;

import javax.xml.bind.annotation.XmlRootElement;

@RooJavaBean
@XmlRootElement(name="purchaseorder")
public class PurchaseOrderResource extends ExtendedResourceSupport {

	private Long internalId;
    private String buildit;
	private String siteAddress;
	private OrderStatus status;
    private PurchaseOrderLineResourceList purchaseOrderLines;

}
