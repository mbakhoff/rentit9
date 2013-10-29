package esi.rentit9.soap;

import org.springframework.roo.addon.javabean.RooJavaBean;

import java.util.Calendar;

@RooJavaBean
public class PurchaseOrderLineResource {
    private String plantId;
    private Calendar startDate;
    private Calendar endDate;
    private Float totalPrice;

}
