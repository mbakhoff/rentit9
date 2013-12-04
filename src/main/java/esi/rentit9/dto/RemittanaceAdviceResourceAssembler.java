package esi.rentit9.dto;

import esi.rentit9.domain.RemittanceAdvice;

public class RemittanaceAdviceResourceAssembler {

    public RemittanaceAdviceResource toResource(RemittanceAdvice advice) {
        RemittanaceAdviceResource res = new RemittanaceAdviceResource();
        res.setRentitInvoiceId(advice.getInvoice().getId());
        res.setPaymentDate(advice.getPayDay());
        return res;
    }

}
