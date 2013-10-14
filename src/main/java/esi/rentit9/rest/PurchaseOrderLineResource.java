package esi.rentit9.rest;

import org.springframework.roo.addon.javabean.RooJavaBean;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: kmetsalu
 * Date: 10/14/13
 * Time: 1:24 PM
 * To change this template use File | Settings | File Templates.
 */
@RooJavaBean
@XmlRootElement(name = "purchaseorderline")
public class PurchaseOrderLineResource {
    private int id;
    private String plantId;
    private Calendar startDate;
    private Calendar endDate;
    private float total_price;
}
