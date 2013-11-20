package esi.rentit9.rest.controller;

import esi.rentit9.RBAC;
import esi.rentit9.domain.Plant;
import esi.rentit9.rest.PlantResource;
import esi.rentit9.rest.PlantResourceAssembler;
import esi.rentit9.rest.PlantResourceList;
import esi.rentit9.rest.util.HttpHelpers;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/rest/")
public class PlantResourceController {

	private PlantResourceAssembler assembler;

	public PlantResourceController() {
		assembler = new PlantResourceAssembler();
	}

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<String>(HttpHelpers.getStack(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }

	@RequestMapping("plants")
	public ResponseEntity<PlantResourceList> getAllPlants() {
		List<Plant> plants = Plant.findAllPlants();
		ResponseEntity<PlantResourceList> response =
				new ResponseEntity<PlantResourceList>(assembler.toResource(plants), HttpStatus.OK);
		return response;
	}

    @RequestMapping(value="plants/find", method = RequestMethod.GET)
    public ResponseEntity<PlantResourceList> getInTimerange(HttpServletRequest request) {
        List<Plant> plants = Plant.getPlantsBetween(
                request.getParameter("name"),
                parseDate(request.getParameter("start")),
                parseDate(request.getParameter("end")));
        PlantResourceList resources = assembler.toResource(plants);
        return new ResponseEntity<PlantResourceList>(resources, HttpStatus.OK);
    }

    private Calendar parseDate(String dateString) {
        return ISODateTimeFormat.yearMonthDay().parseDateTime(dateString).toGregorianCalendar();
    }

    @RequestMapping(value = "plants", method = RequestMethod.POST)
	public ResponseEntity<Void> createPlantResource(@RequestBody PlantResource res) {
        RBAC.assertAuthority(RBAC.ROLE_ADMIN);

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

	@RequestMapping("plants/{id}")
	public ResponseEntity<PlantResource> getById(@PathVariable Long id) {
		Plant plant = Plant.findPlant(id);
		ResponseEntity<PlantResource> response =
				new ResponseEntity<PlantResource>(assembler.toResource(plant), HttpStatus.OK);
		return response;
	}

}
