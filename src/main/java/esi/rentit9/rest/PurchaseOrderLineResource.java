package esi.rentit9.rest;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;

@XmlRootElement(name = "purchaseorderline")
public class PurchaseOrderLineResource {

    public String plantId;
    public Calendar startDate;
    public Calendar endDate;
    public float totalPrice;

}
