package esi.rentit9.rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "orderlines")
public class PurchaseOrderLineListResource {

	@XmlElement(name = "line")
	public Set<PurchaseOrderLineResource> purchaseOrders;

	public PurchaseOrderLineListResource() {
		this.purchaseOrders = new HashSet<PurchaseOrderLineResource>();
	}

}
