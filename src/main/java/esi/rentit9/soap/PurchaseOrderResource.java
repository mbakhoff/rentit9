package esi.rentit9.soap;

import esi.rentit9.domain.OrderStatus;
import org.springframework.roo.addon.javabean.RooJavaBean;

import java.util.ArrayList;
import java.util.List;

@RooJavaBean
public class PurchaseOrderResource {
    private Long internalId;
    private String senderSideId;
    private String buildit;
    private String siteAddress;
    private OrderStatus status;
    private List<PurchaseOrderLineResource> purchaseOrderLines = new ArrayList<PurchaseOrderLineResource>();
}
