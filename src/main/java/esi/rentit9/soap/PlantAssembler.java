package esi.rentit9.soap;

import esi.rentit9.domain.Plant;

import java.util.List;

public class PlantAssembler {
    public PlantResource toResource(Plant plant) {
        PlantResource res = new PlantResource();
        res.setId(plant.getId());
        res.setName(plant.getName());
        res.setDescription(plant.getDescription());
        res.setPrice(plant.getPrice());
        return res;
    }

    public PlantListResource toResource(List<Plant> plants) {
        PlantListResource list = new PlantListResource();

        for (Plant p : plants) {
            list.plants.add(toResource(p));

        }

        return list;
    }
}
