package esi.rentit9.dto;

import esi.rentit9.domain.Plant;

import java.util.ArrayList;
import java.util.List;

public class PlantResourceAssembler {

	public PlantResource toResource(Plant plant) {
		PlantResource res = new PlantResource();
        res.setId(plant.getId());
		res.setName(plant.getName());
		res.setDescription(plant.getDescription());
		res.setPrice(plant.getPrice());
		return res;
	}

    public List<PlantResource> toResource(List<Plant> plants) {
        List<PlantResource> resources = new ArrayList<PlantResource>();
        for (Plant plant : plants) {
            resources.add(toResource(plant));
        }
        return resources;
    }

}
