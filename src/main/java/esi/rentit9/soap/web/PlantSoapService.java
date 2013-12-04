package esi.rentit9.soap.web;

import esi.rentit9.domain.OrderStatus;
import esi.rentit9.domain.Plant;
import esi.rentit9.domain.PurchaseOrder;
import esi.rentit9.dto.PlantResource;
import esi.rentit9.dto.PlantResourceAssembler;
import esi.rentit9.dto.PurchaseOrderResource;
import esi.rentit9.dto.PurchaseOrderResourceAssembler;
import esi.rentit9.soap.PlantsAvailableRequest;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "PlantSoapService")
public class PlantSoapService {

    private final PlantResourceAssembler plantAssembler;
    private final PurchaseOrderResourceAssembler purchaseOrderAssembler;

    public PlantSoapService() {
        plantAssembler = new PlantResourceAssembler();
        purchaseOrderAssembler = new PurchaseOrderResourceAssembler();
    }

    @WebMethod
    public List<PlantResource> getAllPlants() {
        List<Plant> plants = Plant.findAllPlants();
        return plantAssembler.toResource(plants);
    }

    @WebMethod
    public List<PlantResource> getPlantsBetween(PlantsAvailableRequest request) {
        List<Plant> plants = Plant.getPlantsBetween(request.getNameLike(), request.getStartDate(), request.getEndDate());
        return plantAssembler.toResource(plants);
    }

    @WebMethod
    public PurchaseOrderResource createPurchaseOrder(PurchaseOrderResource request) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrderAssembler.fromResource(purchaseOrder, request);
        purchaseOrder.setStatus(OrderStatus.CREATED);
        purchaseOrder.persist();
        return purchaseOrderAssembler.toResource(purchaseOrder);
    }

    @WebMethod
    public PurchaseOrderResource updatePurchaseOrder(PurchaseOrderResource request) {
        PurchaseOrder purchaseOrder = PurchaseOrder.findPurchaseOrder(Long.parseLong(request.getRentitOrderId()));
        if (purchaseOrder == null) {
            return request;
        } else {
            purchaseOrderAssembler.fromResource(purchaseOrder, request);
            purchaseOrder.persist();
            return purchaseOrderAssembler.toResource(purchaseOrder);
        }
    }
}
