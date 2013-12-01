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
     * plants dispatched,
     * plants delivered,
     * plants rejected by customer,
     * plants returned,
     * invoiced
     **/

}
