package esi.rentit9.soap.web;

import esi.rentit9.domain.Plant;
import esi.rentit9.soap.PlantAssembler;
import esi.rentit9.soap.PlantListResource;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "PlantSoapService")
public class PlantSoapService {
    @WebMethod
    public PlantListResource getAllPlants(){
        List<Plant> plants= Plant.findAllPlants();
        PlantAssembler assembler=new PlantAssembler();

        PlantListResource resource=assembler.toResource(plants);
        return resource;
    }
}
