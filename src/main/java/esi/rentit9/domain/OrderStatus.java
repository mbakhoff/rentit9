package esi.rentit9.domain;

public enum OrderStatus {

    CREATED,
    APPROVED, // == ACCEPTED
    REJECTED,
    CANCELLED, // == PLANT_REJECTED_BY_CUSTOMER,
    PLANT_DISPATCHED, //
    PLANT_DELIVERED, //
    PLANT_RETURNED, //
    INVOICED //
    
    
    /*add CREATED when recieved from buildit
     * PO accepted, 
     * PO rejected, 
     * plant dispatched, 
     * plant delivered, 
     * plant rejected by customer, 
     * plant returned, 
     * invoiced
     **/

}
