package esi.rentit9.rest;

import esi.rentit9.domain.OrderStatus;
import org.springframework.hateoas.ResourceSupport;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="purchaseorder")
public class PurchaseOrderResource extends ResourceSupport {

    // naming this to id would conflict with hateoas
    @XmlElement(name = "id")
	public Long internalId;

    public String buildit;
	public String siteAddress;
	public OrderStatus status;
    public PurchaseOrderLineResourceList purchaseOrderLines;

}
