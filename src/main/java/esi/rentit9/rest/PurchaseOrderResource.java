package esi.rentit9.rest;

import org.springframework.roo.addon.javabean.RooJavaBean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: kmetsalu
 * Date: 10/14/13
 * Time: 1:22 PM
 * To change this template use File | Settings | File Templates.
 */
@RooJavaBean
@XmlRootElement(name="purchaseorder")
public class PurchaseOrderResource {
    private int id;
    private String buildit;
    private PurchaseOrderLineListResource purchaseOrderLines;
}
