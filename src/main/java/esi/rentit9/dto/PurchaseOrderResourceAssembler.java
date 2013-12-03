package esi.rentit9.dto;

import esi.rentit9.domain.BuildIt;
import esi.rentit9.domain.PurchaseOrder;
import org.springframework.dao.DataRetrievalFailureException;

import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderResourceAssembler {

    public PurchaseOrderResource toResource(PurchaseOrder order) {
        PurchaseOrderResource res = new PurchaseOrderResource();
        res.setInternalId(order.getId());
        res.setBuildit(order.getBuildit().getUrl());
        res.setSiteAddress(order.getSiteAddress());
        res.setStatus(order.getStatus());
        res.setTotal(order.getTotal());
        res.setPlant(order.getPlant().getId().toString());
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

    public PurchaseOrder fromResource(PurchaseOrder purchaseOrder, PurchaseOrderResource orderResource) {
        purchaseOrder.setBuildit(getOrCreateBuildit(orderResource.getBuildit()));
        purchaseOrder.setSiteAddress(orderResource.getSiteAddress());
        purchaseOrder.setStatus(orderResource.getStatus());
        purchaseOrder.setSenderSideId(orderResource.getSenderSideId());
        purchaseOrder.setPlant(orderResource.getPlantObject());
        purchaseOrder.setTotal(orderResource.getTotal());
        purchaseOrder.setStartDate(orderResource.getStartDate());
        purchaseOrder.setEndDate(orderResource.getEndDate());
        return purchaseOrder;
    }

    private BuildIt getOrCreateBuildit(String buildit) {
        try {
            return BuildIt.getByUrl(buildit);
        } catch (DataRetrievalFailureException ex) {
            BuildIt buildIt = new BuildIt();
            buildIt.setUrl(buildit);
            buildIt.persist();
            return buildIt;
        }
    }
}
