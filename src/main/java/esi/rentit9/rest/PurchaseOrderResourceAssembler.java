package esi.rentit9.rest;

import esi.rentit9.domain.PurchaseOrder;
import esi.rentit9.domain.PurchaseOrderLine;

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
		res.setPurchaseOrderLines(lineAssembler.toResource(getLines(order)));
		return res;
	}

	private List<PurchaseOrderLine> getLines(PurchaseOrder order) {
		// TODO: optimize this
		List<PurchaseOrderLine> all = PurchaseOrderLine.findAllPurchaseOrderLines();
		List<PurchaseOrderLine> matches = new ArrayList<PurchaseOrderLine>();
		for (PurchaseOrderLine line : all) {
			if (line.getPurchaseOrder().getId().equals(order.getId())) {
				matches.add(line);
			}
		}
		return matches;
	}

	public List<PurchaseOrderResource> toResource(List<PurchaseOrder> orders) {
		List<PurchaseOrderResource> all = new ArrayList<PurchaseOrderResource>();
		for (PurchaseOrder order : orders) {
			all.add(toResource(order));
		}
		return all;
	}

}
