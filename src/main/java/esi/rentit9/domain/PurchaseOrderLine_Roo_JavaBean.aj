// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package esi.rentit9.domain;

import esi.rentit9.domain.Plant;
import esi.rentit9.domain.PurchaseOrder;
import esi.rentit9.domain.PurchaseOrderLine;
import java.util.Calendar;

privileged aspect PurchaseOrderLine_Roo_JavaBean {
    
    public PurchaseOrder PurchaseOrderLine.getPurchaseOrder() {
        return this.purchaseOrder;
    }
    
    public void PurchaseOrderLine.setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }
    
    public Plant PurchaseOrderLine.getPlant() {
        return this.plant;
    }
    
    public void PurchaseOrderLine.setPlant(Plant plant) {
        this.plant = plant;
    }
    
    public Calendar PurchaseOrderLine.getStartDate() {
        return this.startDate;
    }
    
    public void PurchaseOrderLine.setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }
    
    public Calendar PurchaseOrderLine.getEndDate() {
        return this.endDate;
    }
    
    public void PurchaseOrderLine.setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }
    
}
