package esi.rentit9.rest;

import esi.rentit9.domain.PurchaseOrderLine;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kmetsalu
 * Date: 10/14/13
 * Time: 1:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class PurchaseOrderLineAssembler {

    public PurchaseOrderLineResource toResource(PurchaseOrderLine line){
       //TODO: Convert POL to POLR
       return null;
    }

    public PurchaseOrderLine fromResource(PurchaseOrderLineResource line){
        //TODO: Convert POLR to POL
        return null;
    }

    public PurchaseOrderLineListResource toResource(List<PurchaseOrderLine> lines){
        //TODO: Conversion to POLLR
        return null;
    }

    public List<PurchaseOrderLine> fromResource(PurchaseOrderLineListResource line){
        //TODO: Conversion of POLLR
        return null;
    }


}
