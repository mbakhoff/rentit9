// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package esi.rentit9.rest;

import esi.rentit9.rest.RemittanaceAdviceResource;
import java.util.Calendar;

privileged aspect RemittanaceAdviceResource_Roo_JavaBean {
    
    public Long RemittanaceAdviceResource.getInvoiceId() {
        return this.invoiceId;
    }
    
    public void RemittanaceAdviceResource.setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }
    
    public Calendar RemittanaceAdviceResource.getPayDay() {
        return this.payDay;
    }
    
    public void RemittanaceAdviceResource.setPayDay(Calendar payDay) {
        this.payDay = payDay;
    }
    
}