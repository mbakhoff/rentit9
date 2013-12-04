package esi.rentit9.dto;

import esi.rentit9.domain.BuildIt;
import esi.rentit9.domain.PurchaseOrder;

import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderResourceAssembler {

    public PurchaseOrderResource toResource(PurchaseOrder order) {
        PurchaseOrderResource res = new PurchaseOrderResource();
        res.setRentitOrderId(order.getId().toString());
        res.setBuilditOrderId(order.getSenderSideId());
        res.setBuildit(order.getBuildit().getName());
        res.setRentit("rentit9");
        res.setSiteAddress(order.getSiteAddress());
        res.setStatus(order.getStatus().toString());
        res.setTotal(order.getTotal());
        res.setPlantId(order.getPlant().getId().toString());
        res.setStartDate(order.getStartDate());
        res.setEndDate(order.getEndDate());
        return res;
    }

    public List<PurchaseOrderResource> toResource(List<PurchaseOrder> orders) {
        List<PurchaseOrderResource> resources = new ArrayList<PurchaseOrderResource>();
        for (PurchaseOrder order : orders) {
            resources.add(toResource(order));
        }
        return resources;
    }

    public void fromResource(PurchaseOrder purchaseOrder, PurchaseOrderResource orderResource) {
        purchaseOrder.setBuildit(BuildIt.getOrCreate(orderResource.getBuildit()));
        purchaseOrder.setSiteAddress(orderResource.getSiteAddress());
        purchaseOrder.setSenderSideId(orderResource.getBuilditOrderId());
        purchaseOrder.setPlant(orderResource.getPlantObject());
        purchaseOrder.setTotal(orderResource.getTotal());
        purchaseOrder.setStartDate(orderResource.getStartDate());
        purchaseOrder.setEndDate(orderResource.getEndDate());
    }

}
