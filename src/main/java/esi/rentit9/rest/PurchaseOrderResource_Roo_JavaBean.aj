// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package esi.rentit9.rest;

import esi.rentit9.rest.PurchaseOrderLineResourceList;
import esi.rentit9.rest.PurchaseOrderResource;

privileged aspect PurchaseOrderResource_Roo_JavaBean {
    
    public String PurchaseOrderResource.getBuildit() {
        return this.buildit;
    }
    
    public void PurchaseOrderResource.setBuildit(String buildit) {
        this.buildit = buildit;
    }
    
    public String PurchaseOrderResource.getSiteAddress() {
        return this.siteAddress;
    }
    
    public void PurchaseOrderResource.setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }
    
    public PurchaseOrderLineResourceList PurchaseOrderResource.getPurchaseOrderLines() {
        return this.purchaseOrderLines;
    }
    
    public void PurchaseOrderResource.setPurchaseOrderLines(PurchaseOrderLineResourceList purchaseOrderLines) {
        this.purchaseOrderLines = purchaseOrderLines;
    }
    
}
