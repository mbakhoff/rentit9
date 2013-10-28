package esi.rentit9.soap;

import esi.rentit9.domain.Plant;
import esi.rentit9.domain.PurchaseOrder;
import esi.rentit9.domain.PurchaseOrderLine;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
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

    public PurchaseOrderLineResourceList toResource(Set<PurchaseOrderLine> lines) {
        PurchaseOrderLineResourceList all = new PurchaseOrderLineResourceList();
        for (PurchaseOrderLine line : lines) {
            all.purchaseOrderLines.add(toResource(line));
        }
        return all;
    }

    public Set<PurchaseOrderLine> fromResource(PurchaseOrder purchaseOrder, PurchaseOrderLineResourceList lines){
        Set<PurchaseOrderLine> result=new HashSet<PurchaseOrderLine>();
        for(PurchaseOrderLineResource line: lines.purchaseOrderLines){
            result.add(fromResource(purchaseOrder,line));
        }
        return result;
    }

    private PurchaseOrderLine fromResource(PurchaseOrder purchaseOrder,PurchaseOrderLineResource line) {
        PurchaseOrderLine result=new PurchaseOrderLine();
        result.setStartDate(line.getStartDate());
        result.setEndDate(line.getEndDate());
        result.setPlant(Plant.findPlant(Long.valueOf(line.getPlantId())));
        result.setPurchaseOrder(purchaseOrder);
        result.setTotal(line.getTotalPrice());

        return result;  //To change body of created methods use File | Settings | File Templates.
    }
}
