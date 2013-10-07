package esi.rentit9.rest;

import esi.rentit9.domain.Plant;

import java.util.ArrayList;
import java.util.List;

public class PlantResourceAssembler {

	public PlantResource toResource(Plant plant) {
		PlantResource res = new PlantResource();
		res.setName(plant.getName());
		res.setDescription(plant.getDescription());
		res.setPrice(plant.getPrice());
		return res;
	}

	public PlantResourceList toResource(List<Plant> plants) {
		PlantResourceList list = new PlantResourceList();
		List<PlantResource> resources = new ArrayList<PlantResource>();
		for (Plant p : plants) {
			resources.add(toResource(p));
		}
		list.setPlant(resources);
		return list;
	}

}
