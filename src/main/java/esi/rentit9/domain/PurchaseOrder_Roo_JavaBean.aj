// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package esi.rentit9.domain;

import esi.rentit9.domain.BuildIt;
import esi.rentit9.domain.PurchaseOrder;

privileged aspect PurchaseOrder_Roo_JavaBean {
    
    public BuildIt PurchaseOrder.getBuildit() {
        return this.buildit;
    }
    
    public void PurchaseOrder.setBuildit(BuildIt buildit) {
        this.buildit = buildit;
    }
    
    public Boolean PurchaseOrder.getApproved() {
        return this.approved;
    }
    
    public void PurchaseOrder.setApproved(Boolean approved) {
        this.approved = approved;
    }
    
    public String PurchaseOrder.getSiteAddress() {
        return this.siteAddress;
    }
    
    public void PurchaseOrder.setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }
    
}