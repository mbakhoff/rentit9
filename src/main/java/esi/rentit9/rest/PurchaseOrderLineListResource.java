package esi.rentit9.rest;

import org.springframework.roo.addon.javabean.RooJavaBean;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kmetsalu
 * Date: 10/14/13
 * Time: 1:26 PM
 * To change this template use File | Settings | File Templates.
 */
@RooJavaBean
@XmlRootElement(name = "purchaseorders")
public class PurchaseOrderLineListResource {
    private List<PurchaseOrderLineResource> line;
}
