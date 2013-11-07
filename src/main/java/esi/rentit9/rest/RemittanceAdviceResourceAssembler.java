package esi.rentit9.rest;

import esi.rentit9.domain.RemittanceAdvice;
import esi.rentit9.rest.controller.RemittanceAdviceRestController;
import esi.rentit9.rest.util.MethodLookupHelper;

public class RemittanceAdviceResourceAssembler {

    private final MethodLookupHelper linker;

	public RemittanceAdviceResourceAssembler() {
        linker = new MethodLookupHelper(RemittanceAdviceRestController.class);
	}

	public RemittanaceAdviceResource toResource(RemittanceAdvice rem) {
		RemittanaceAdviceResource res = new RemittanaceAdviceResource();
		res.setInvoiceId(rem.getInvoice().getId());
		res.setPayDay(rem.getPayDay());
		return res;
	}

}
