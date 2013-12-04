package esi.rentit9.rest.controller;

import esi.rentit9.RBAC;
import esi.rentit9.domain.Plant;
import esi.rentit9.dto.PlantResource;
import esi.rentit9.dto.PlantResourceAssembler;
import esi.rentit9.dto.PlantResourceList;
import esi.rentit9.rest.util.HttpHelpers;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
        if (ex instanceof RBAC.UnauthorizedAccessException) {
            return new ResponseEntity<String>(HttpHelpers.getStack(ex), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<String>(HttpHelpers.getStack(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }

	@RequestMapping("plants")
	public ResponseEntity<PlantResourceList> getAllPlants() {
        List<Plant> plants = Plant.findAllPlants();

        PlantResourceList resourceList = new PlantResourceList(assembler.toResource(plants));
        return new ResponseEntity<PlantResourceList>(resourceList, HttpStatus.OK);
	}

    @RequestMapping(value="plants/find", method = RequestMethod.GET)
    public ResponseEntity<PlantResourceList> getInTimerange(HttpServletRequest request) {
        List<Plant> plants = Plant.getPlantsBetween(
                request.getParameter("name"),
                parseDate(request.getParameter("start")),
                parseDate(request.getParameter("end")));

        PlantResourceList resourceList = new PlantResourceList(assembler.toResource(plants));
        return new ResponseEntity<PlantResourceList>(resourceList, HttpStatus.OK);
    }

    private static Calendar parseDate(String dateString) {
        return ISODateTimeFormat.yearMonthDay().parseDateTime(dateString).toGregorianCalendar();
    }

    @RequestMapping(value = "plants", method = RequestMethod.POST)
	public ResponseEntity<PlantResource> createPlantResource(@RequestBody PlantResource res) {
        RBAC.assertAuthority(RBAC.ROLE_ADMIN);

		Plant p = new Plant();
		p.setDescription(res.getDescription());
		p.setName(res.getName());
		p.setPrice(res.getPrice());
		p.persist();

        HttpHeaders headers = new HttpHeaders();
        headers.add("EntityId", p.getId().toString());
        PlantResource resource = assembler.toResource(p);
        return new ResponseEntity<PlantResource>(resource, headers, HttpStatus.CREATED);
	}

	@RequestMapping("plants/{id}")
	public ResponseEntity<PlantResource> getById(@PathVariable Long id) {
        PlantResource resource = assembler.toResource(Plant.findPlant(id));
        return new ResponseEntity<PlantResource>(resource, HttpStatus.OK);
	}

}
