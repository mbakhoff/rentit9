package esi.rentit9.rest;

import esi.rentit9.domain.PurchaseOrder;
import esi.rentit9.rest.controller.PurchaseOrderRestController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderResourceAssembler extends ResourceAssemblerSupport<PurchaseOrder, PurchaseOrderResource> {

	private PurchaseOrderLineAssembler lineAssembler;

	public PurchaseOrderResourceAssembler() {
        super(PurchaseOrderRestController.class, PurchaseOrderResource.class);
        lineAssembler = new PurchaseOrderLineAssembler();
	}

	public PurchaseOrderResource toResource(PurchaseOrder order) {
		PurchaseOrderResource res = new PurchaseOrderResource();
		res.setInternalId(order.getId());
		res.setBuildit(order.getBuildit().getUrl());
		res.setSiteAddress(order.getSiteAddress());
		res.setStatus(order.getStatus());
		res.setPurchaseOrderLines(lineAssembler.toResource(order.getLines()));
		return res;
	}

	public List<PurchaseOrderResource> toResource(List<PurchaseOrder> orders) {
		List<PurchaseOrderResource> all = new ArrayList<PurchaseOrderResource>();
		for (PurchaseOrder order : orders) {
			all.add(toResource(order));
		}
		return all;
	}

}
