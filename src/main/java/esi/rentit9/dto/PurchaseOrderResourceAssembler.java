package esi.rentit9.dto;

import esi.rentit9.domain.BuildIt;
import esi.rentit9.domain.Plant;
import esi.rentit9.domain.PurchaseOrder;
import esi.rentit9.domain.PurchaseOrderLine;
import org.springframework.dao.DataRetrievalFailureException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PurchaseOrderResourceAssembler {

    public PurchaseOrderResource toResource(PurchaseOrder order) {
        PurchaseOrderResource res = new PurchaseOrderResource();
        res.setInternalId(order.getId());
        res.setBuildit(order.getBuildit().getUrl());
        res.setSiteAddress(order.getSiteAddress());
        res.setStatus(order.getStatus());
        res.setTotal(order.getTotal());
        res.setPlants(plantIds(order));
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

    private List<String> plantIds(PurchaseOrder order) {
        List<String> ids = new ArrayList<String>();
        for (PurchaseOrderLine line : order.getLines()) {
            ids.add(line.getPlant().getId().toString());
        }
        return ids;
    }

    public PurchaseOrder fromResource(PurchaseOrder purchaseOrder, PurchaseOrderResource orderResource) {
        purchaseOrder.setBuildit(getOrCreateBuildit(orderResource.getBuildit()));
        purchaseOrder.setSiteAddress(orderResource.getSiteAddress());
        purchaseOrder.setStatus(orderResource.getStatus());
        purchaseOrder.setSenderSideId(orderResource.getSenderSideId());
        purchaseOrder.setLines(getOrderLines(purchaseOrder, orderResource.getPlants()));
        purchaseOrder.setTotal(orderResource.getTotal());
        purchaseOrder.setStartDate(orderResource.getStartDate());
        purchaseOrder.setEndDate(orderResource.getEndDate());
        return purchaseOrder;
    }

    public Set<PurchaseOrderLine> getOrderLines(PurchaseOrder purchaseOrder, List<String> plants) {
        Set<PurchaseOrderLine> lines = new HashSet<PurchaseOrderLine>();
        for (String plantId : plants) {
            PurchaseOrderLine line = new PurchaseOrderLine();
            line.setPurchaseOrder(purchaseOrder);
            line.setPlant(Plant.findPlant(Long.parseLong(plantId)));
            line.persist();
            lines.add(line);
        }
        return lines;
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
