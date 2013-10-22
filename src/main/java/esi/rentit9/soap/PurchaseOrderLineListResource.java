package esi.rentit9.soap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "purchaseorderlines")
public class PurchaseOrderLineListResource {

    @XmlElement(name = "purchaseorderline")
    public Set<PurchaseOrderLineResource> purchaseOrderLines;

    public PurchaseOrderLineListResource() {
        purchaseOrderLines = new HashSet<PurchaseOrderLineResource>();
    }
}
