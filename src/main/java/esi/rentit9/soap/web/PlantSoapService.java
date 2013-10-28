package esi.rentit9.soap.web;

import esi.rentit9.domain.Plant;
import esi.rentit9.soap.GetPlantsBetweenResource;
import esi.rentit9.soap.GetPlantsResource;
import esi.rentit9.soap.PlantAssembler;
import esi.rentit9.soap.PlantResourceList;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "PlantSoapService")
public class PlantSoapService {

    private final PlantAssembler plantAssembler;

    public PlantSoapService() {
        plantAssembler = new PlantAssembler();
    }

    @WebMethod
    public PlantResourceList getAllPlants(GetPlantsResource request){
        List<Plant> plants= Plant.findAllPlants();
        PlantResourceList resource=plantAssembler.toResource(plants);
        return resource;
    }

    @WebMethod
    public PlantResourceList getPlantsBetween(GetPlantsBetweenResource request){
        List<Plant> plants = Plant.getPlantsBetween(request.getNameLike(), request.getStartDate(),request.getEndDate());
        PlantResourceList asResource = plantAssembler.toResource(plants);
        return asResource;
    }
}
