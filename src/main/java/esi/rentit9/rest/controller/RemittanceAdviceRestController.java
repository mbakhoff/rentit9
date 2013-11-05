package esi.rentit9.rest.controller;

import esi.rentit9.domain.RemittanceAdvice;
import esi.rentit9.rest.RemittanaceAdviceResource;
import esi.rentit9.rest.util.MethodLookup;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("/rest/")
public class RemittanceAdviceRestController {
    public static final int METHOD_CREATE_RA = 1;

    @RequestMapping(value = "ra", method = RequestMethod.POST)
    @MethodLookup(METHOD_CREATE_RA)
	public ResponseEntity<Void> createRemittance(@RequestBody RemittanaceAdviceResource res) {
		RemittanceAdvice remittanceAdvice = new RemittanceAdvice();
		remittanceAdvice.setInvoice(res.getInvoice());
		remittanceAdvice.setPayDay(res.getPayDay());
		remittanceAdvice.persist();

		HttpHeaders headers = new HttpHeaders();
		URI location =
				ServletUriComponentsBuilder.fromCurrentRequestUri().
						pathSegment(remittanceAdvice.getId().toString()).build().toUri();
		headers.setLocation(location);
		headers.add("RentItId", remittanceAdvice.getId().toString());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
}
