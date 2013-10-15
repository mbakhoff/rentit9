package esi.rentit9.rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "orderlines")
public class PurchaseOrderLineResourceList {

	@XmlElement(name = "line")
	public List<PurchaseOrderLineResource> purchaseOrders;

	public PurchaseOrderLineResourceList() {
		this.purchaseOrders = new ArrayList<PurchaseOrderLineResource>();
	}

}
