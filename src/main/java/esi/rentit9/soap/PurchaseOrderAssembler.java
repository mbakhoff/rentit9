package esi.rentit9.soap;

import esi.rentit9.domain.BuildIt;
import esi.rentit9.domain.PurchaseOrder;

import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderAssembler {
    private PurchaseOrderLineAssembler lineAssembler;

    public PurchaseOrderAssembler() {
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

    public PurchaseOrder fromResource(PurchaseOrder purchaseOrder,PurchaseOrderResource orderResource){
        purchaseOrder.setBuildit(BuildIt.getByUrl(orderResource.getBuildit()));
        purchaseOrder.setSiteAddress(orderResource.getSiteAddress());
        purchaseOrder.setStatus(orderResource.getStatus());
        purchaseOrder.setSenderSideId(orderResource.getSenderSideId());

        purchaseOrder.setLines(lineAssembler.fromResource(purchaseOrder,orderResource.getPurchaseOrderLines()));

        return purchaseOrder;
    }
}
