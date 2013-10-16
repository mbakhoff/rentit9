package esi.rentit9.rest;

import esi.rentit9.domain.PurchaseOrder;

import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderResourceAssembler {

	private PurchaseOrderLineAssembler lineAssembler;

	public PurchaseOrderResourceAssembler() {
		lineAssembler = new PurchaseOrderLineAssembler();
	}

	public PurchaseOrderResource toResource(PurchaseOrder order) {
		PurchaseOrderResource res = new PurchaseOrderResource();
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
