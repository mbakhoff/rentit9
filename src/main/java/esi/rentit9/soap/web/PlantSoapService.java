package esi.rentit9.soap.web;

import esi.rentit9.domain.BuildIt;
import esi.rentit9.domain.Plant;
import esi.rentit9.domain.PurchaseOrder;
import esi.rentit9.soap.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "PlantSoapService")
public class PlantSoapService {

    private final PlantAssembler plantAssembler;
    private final PurchaseOrderAssembler purchaseOrderAssembler;

    public PlantSoapService() {
        plantAssembler = new PlantAssembler();
        purchaseOrderAssembler = new PurchaseOrderAssembler();
    }

    @WebMethod
    public PlantResourceList getAllPlants(GetPlantsResource request){
        List<Plant> plants= Plant.findAllPlants();
        PlantResourceList resource=plantAssembler.toResource(plants);
        return resource;
    }

    @WebMethod
    public PlantResourceList getPlantsBetween(GetPlantsBetweenResource request){
        List<Plant> plants = Plant.getPlantsBetween(request.getNameLike(), request.getStartDate(), request.getEndDate());
        PlantResourceList asResource = plantAssembler.toResource(plants);
        return asResource;
    }

    @WebMethod
    public PurchaseOrderResource createPurchaseOrder(PurchaseOrderResource request){
        PurchaseOrder purchaseOrder=new PurchaseOrder();
        //purchaseOrder.setId(orderResource.getInternalId());
        purchaseOrder = purchaseOrderAssembler.fromResource(purchaseOrder,request);
        purchaseOrder.persist();
        return purchaseOrderAssembler.toResource(purchaseOrder);
    }

    @WebMethod
    public PurchaseOrderResource updatePurchaseOrder(PurchaseOrderResource request){
        PurchaseOrder purchaseOrder = PurchaseOrder.findPurchaseOrder(request.getInternalId());
        if (purchaseOrder==null){
            return request;
        }
        else {
            purchaseOrder = purchaseOrderAssembler.fromResource(purchaseOrder,request);
            purchaseOrder.persist();
            return purchaseOrderAssembler.toResource(purchaseOrder);
        }
    }
}
