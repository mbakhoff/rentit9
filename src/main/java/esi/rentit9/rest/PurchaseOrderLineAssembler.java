package esi.rentit9.rest;

import esi.rentit9.domain.Plant;
import esi.rentit9.domain.PurchaseOrderLine;

import java.util.List;

public class PurchaseOrderLineAssembler {

    public PurchaseOrderLineResource toResource(PurchaseOrderLine line) {
        PurchaseOrderLineResource res = new PurchaseOrderLineResource();
        res.plantId = getPlantId(line);
        res.startDate = line.getStartDate();
        res.endDate = line.getEndDate();
        res.totalPrice = line.getTotal();
        return res;
    }

    private String getPlantId(PurchaseOrderLine line) {
        Plant plant = line.getPlant();
        if (plant != null) {
            return plant.getId().toString();
        }
        return null;
    }

    public PurchaseOrderLineResourceList toResource(List<PurchaseOrderLine> lines) {
        PurchaseOrderLineResourceList all = new PurchaseOrderLineResourceList();
        for (PurchaseOrderLine line : lines) {
            all.purchaseOrders.add(toResource(line));
        }
        return all;
    }

}
