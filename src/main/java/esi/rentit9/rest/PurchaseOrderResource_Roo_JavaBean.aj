// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package esi.rentit9.rest;

import esi.rentit9.domain.OrderStatus;

privileged aspect PurchaseOrderResource_Roo_JavaBean {
    
    public Long PurchaseOrderResource.getInternalId() {
        return this.internalId;
    }
    
    public void PurchaseOrderResource.setInternalId(Long internalId) {
        this.internalId = internalId;
    }
    
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
    
    public OrderStatus PurchaseOrderResource.getStatus() {
        return this.status;
    }
    
    public void PurchaseOrderResource.setStatus(OrderStatus status) {
        this.status = status;
    }
    
    public PurchaseOrderLineResourceList PurchaseOrderResource.getPurchaseOrderLines() {
        return this.purchaseOrderLines;
    }
    
    public void PurchaseOrderResource.setPurchaseOrderLines(PurchaseOrderLineResourceList purchaseOrderLines) {
        this.purchaseOrderLines = purchaseOrderLines;
    }
    
}
