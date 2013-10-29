package esi.rentit9.soap;

import esi.rentit9.domain.Plant;
import esi.rentit9.domain.PurchaseOrder;
import esi.rentit9.domain.PurchaseOrderLine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    public List<PurchaseOrderLineResource> toResource(Set<PurchaseOrderLine> lines) {
        List<PurchaseOrderLineResource> all = new ArrayList<PurchaseOrderLineResource>();
        for (PurchaseOrderLine line : lines) {
            all.add(toResource(line));
        }
        return all;
    }

    public Set<PurchaseOrderLine> fromResource(PurchaseOrder purchaseOrder, List<PurchaseOrderLineResource> lines) {
        Set<PurchaseOrderLine> result = new HashSet<PurchaseOrderLine>();
        for (PurchaseOrderLineResource line : lines) {
            result.add(fromResource(purchaseOrder, line));
        }
        return result;
    }

    private PurchaseOrderLine fromResource(PurchaseOrder purchaseOrder, PurchaseOrderLineResource line) {
        PurchaseOrderLine result = new PurchaseOrderLine();
        result.setStartDate(line.getStartDate());
        result.setEndDate(line.getEndDate());
        result.setPlant(Plant.findPlant(Long.valueOf(line.getPlantId())));
        result.setPurchaseOrder(purchaseOrder);
        result.setTotal(line.getTotalPrice());
        return result;
    }
}
