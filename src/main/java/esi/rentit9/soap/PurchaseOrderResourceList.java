package esi.rentit9.soap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="purchaseorders")
public class PurchaseOrderResourceList {

    @XmlElement(name="purchaseorder")
    public List<PurchaseOrderResource> purchaseOrders;

    public PurchaseOrderResourceList() {
        this.purchaseOrders = new ArrayList<PurchaseOrderResource>();
    }
}
