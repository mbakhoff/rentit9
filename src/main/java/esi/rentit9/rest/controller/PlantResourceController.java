package esi.rentit9.rest.controller;

import esi.rentit9.domain.Plant;
import esi.rentit9.rest.PlantResource;
import esi.rentit9.rest.PlantResourceAssembler;
import esi.rentit9.rest.PlantResourceList;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/rest")
public class PlantResourceController {

	@RequestMapping("plants")
	public ResponseEntity<PlantResourceList> getAllPlants() {
		List<Plant> plants = Plant.findAllPlants();
		PlantResourceAssembler assembler = new PlantResourceAssembler();
		ResponseEntity<PlantResourceList> response =
				new ResponseEntity<PlantResourceList>(assembler.toResource(plants), HttpStatus.OK);
		return response;
	}

	@RequestMapping(value = "plants", method = RequestMethod.POST)
	public ResponseEntity<Void> createPlantResource(@RequestBody PlantResource res) {
		Plant p = new Plant();
		p.setDescription(res.getDescription());
		p.setName(res.getName());
		p.setPrice(res.getPrice());
		p.persist();

		HttpHeaders headers = new HttpHeaders();
		URI location =
				ServletUriComponentsBuilder.fromCurrentRequestUri().
						pathSegment(p.getId().toString()).build().toUri();
		headers.setLocation(location);
		ResponseEntity<Void> response = new
				ResponseEntity<Void>(headers, HttpStatus.CREATED);
		return response;
	}
}
