package esi.rentit9.rest;

import org.springframework.roo.addon.javabean.RooJavaBean;

import javax.xml.bind.annotation.XmlRootElement;

@RooJavaBean
@XmlRootElement(name="purchaseorder")
public class PurchaseOrderResource {

    private String buildit;
	private String siteAddress;
    private PurchaseOrderLineResourceList purchaseOrderLines;

}