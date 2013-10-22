package esi.rentit9.rest;

import esi.rentit9.domain.PurchaseOrder;
import esi.rentit9.rest.controller.PurchaseOrderRestController;
import esi.rentit9.rest.util.ExtendedLink;
import esi.rentit9.rest.util.MethodLookupHelper;

import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderResourceAssembler {

	private final PurchaseOrderLineAssembler lineAssembler;
    private final MethodLookupHelper linker;

	public PurchaseOrderResourceAssembler() {
        lineAssembler = new PurchaseOrderLineAssembler();
        linker = new MethodLookupHelper(PurchaseOrderRestController.class);
	}

	public PurchaseOrderResource toResource(PurchaseOrder order) {
		PurchaseOrderResource res = new PurchaseOrderResource();
        addSelfLink(order, res);
		res.setInternalId(order.getId());
		res.setBuildit(order.getBuildit().getUrl());
		res.setSiteAddress(order.getSiteAddress());
		res.setStatus(order.getStatus());
		res.setPurchaseOrderLines(lineAssembler.toResource(order.getLines()));
		return res;
	}

    private void addSelfLink(PurchaseOrder order, PurchaseOrderResource res) {
        ExtendedLink getById = linker.buildLink(PurchaseOrderRestController.METHOD_GET_BY_ID, order.getId());
        res.add(new ExtendedLink(getById.getHref(), "self", getById.getMethod()));
    }

	public List<PurchaseOrderResource> toResource(List<PurchaseOrder> orders) {
		List<PurchaseOrderResource> all = new ArrayList<PurchaseOrderResource>();
		for (PurchaseOrder order : orders) {
			all.add(toResource(order));
		}
		return all;
	}

}
