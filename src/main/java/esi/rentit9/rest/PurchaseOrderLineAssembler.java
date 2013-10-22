package esi.rentit9.rest;

import esi.rentit9.domain.Plant;
import esi.rentit9.domain.PurchaseOrderLine;

import java.util.Set;

public class PurchaseOrderLineAssembler {

    public PurchaseOrderLineResource toResource(PurchaseOrderLine line) {
       	PurchaseOrderLineResource res = new PurchaseOrderLineResource();
		res.setPlantId(getPlantId(line));
		res.setStartDate(line.getStartDate());
		res.setEndDate(line.getEndDate());
		res.setTotalPrice(line.getTotal());
		return res;
    }

	private String getPlantId(PurchaseOrderLine line) {
		Plant plant = line.getPlant();
		if (plant != null) {
			return plant.getId().toString();
		}
		return null;
	}

	public PurchaseOrderLineListResource toResource(Set<PurchaseOrderLine> lines) {
		PurchaseOrderLineListResource all = new PurchaseOrderLineListResource();
		for (PurchaseOrderLine line : lines) {
			all.purchaseOrders.add(toResource(line));
		}
        return all;
    }

}
