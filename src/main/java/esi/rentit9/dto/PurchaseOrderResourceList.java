package esi.rentit9.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "purchaseorders")
public class PurchaseOrderResourceList {

    @XmlElement(name = "purchaseorder")
    public List<PurchaseOrderResource> orders;

    @SuppressWarnings("UnusedDeclaration")
    public PurchaseOrderResourceList() {
        orders = new ArrayList<PurchaseOrderResource>();
    }

    public PurchaseOrderResourceList(List<PurchaseOrderResource> orders) {
        this.orders = orders;
    }
}
