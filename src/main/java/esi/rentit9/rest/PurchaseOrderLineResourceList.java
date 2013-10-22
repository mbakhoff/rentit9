package esi.rentit9.rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@XmlRootElement(name = "orderlines")
public class PurchaseOrderLineResourceList {

	@XmlElement(name = "line")
	public Set<PurchaseOrderLineResource> purchaseOrders;

	public PurchaseOrderLineResourceList() {
		this.purchaseOrders = new HashSet<PurchaseOrderLineResource>();
	}

}
