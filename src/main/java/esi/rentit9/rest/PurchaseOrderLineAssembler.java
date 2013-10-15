package esi.rentit9.rest;

import esi.rentit9.domain.PurchaseOrderLine;

import java.util.List;

public class PurchaseOrderLineAssembler {

    public PurchaseOrderLineResource toResource(PurchaseOrderLine line) {
       	PurchaseOrderLineResource res = new PurchaseOrderLineResource();
		res.setPlantId(line.getPlant().getId().toString());
		res.setStartDate(line.getStartDate());
		res.setEndDate(line.getEndDate());
		res.setTotalPrice(line.getTotal());
		return res;
    }

    public PurchaseOrderLineResourceList toResource(List<PurchaseOrderLine> lines) {
		PurchaseOrderLineResourceList all = new PurchaseOrderLineResourceList();
		for (PurchaseOrderLine line : lines) {
			all.purchaseOrders.add(toResource(line));
		}
        return all;
    }

}
