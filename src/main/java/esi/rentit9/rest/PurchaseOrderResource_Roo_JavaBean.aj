// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package esi.rentit9.rest;

import esi.rentit9.rest.PurchaseOrderLineListResource;
import esi.rentit9.rest.PurchaseOrderResource;

privileged aspect PurchaseOrderResource_Roo_JavaBean {
    
    public int PurchaseOrderResource.getId() {
        return this.id;
    }
    
    public void PurchaseOrderResource.setId(int id) {
        this.id = id;
    }
    
    public String PurchaseOrderResource.getBuildit() {
        return this.buildit;
    }
    
    public void PurchaseOrderResource.setBuildit(String buildit) {
        this.buildit = buildit;
    }
    
    public PurchaseOrderLineListResource PurchaseOrderResource.getPurchaseOrderLines() {
        return this.purchaseOrderLines;
    }
    
    public void PurchaseOrderResource.setPurchaseOrderLines(PurchaseOrderLineListResource purchaseOrderLines) {
        this.purchaseOrderLines = purchaseOrderLines;
    }
    
}
