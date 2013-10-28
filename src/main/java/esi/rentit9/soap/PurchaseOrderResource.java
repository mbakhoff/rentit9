package esi.rentit9.soap;

import esi.rentit9.domain.OrderStatus;
import org.springframework.roo.addon.javabean.RooJavaBean;

import javax.xml.bind.annotation.XmlRootElement;

@RooJavaBean
@XmlRootElement(name="purchaseorder")
public class PurchaseOrderResource {
    private Long id;
    private String buildit;
    private String siteAddress;
    private OrderStatus status;
    private PurchaseOrderLineResourceList purchaseOrderLines;
}
