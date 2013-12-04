package esi.rentit9.rest.controller;

import esi.rentit9.RBAC;
import esi.rentit9.domain.Invoice;
import esi.rentit9.domain.RemittanceAdvice;
import esi.rentit9.dto.RemittanaceAdviceResource;
import esi.rentit9.dto.RemittanaceAdviceResourceAssembler;
import esi.rentit9.rest.util.HttpHelpers;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/rest/")
public class RemittanceAdviceRestController {

    private final RemittanaceAdviceResourceAssembler assembler;

	public RemittanceAdviceRestController() {
        assembler = new RemittanaceAdviceResourceAssembler();
	}

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        ex.printStackTrace();
        if (ex instanceof RBAC.UnauthorizedAccessException) {
            return new ResponseEntity<String>(HttpHelpers.getStack(ex), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<String>(HttpHelpers.getStack(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @RequestMapping(value = "ra", method = RequestMethod.POST)
	public ResponseEntity<RemittanaceAdviceResource> createRemittance(@RequestBody RemittanaceAdviceResource res) {
        RBAC.assertAuthority(RBAC.ROLE_CLIENT);

        Invoice invoice = Invoice.findInvoice(res.getRentitInvoiceId());
        if (invoice == null) {
            throw new IllegalArgumentException("invoice " + res.getRentitInvoiceId() + " was not found");
        }

		RemittanceAdvice remittanceAdvice = new RemittanceAdvice();
		remittanceAdvice.setInvoice(invoice);
		remittanceAdvice.setPayDay(res.getPaymentDate());
		remittanceAdvice.persist();

		HttpHeaders headers = new HttpHeaders();
		headers.add("EntityId", remittanceAdvice.getId().toString());
        RemittanaceAdviceResource resource = assembler.toResource(remittanceAdvice);
		return new ResponseEntity<RemittanaceAdviceResource>(resource, headers, HttpStatus.CREATED);
	}
}
