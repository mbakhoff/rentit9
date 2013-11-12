package esi.rentit9.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

import java.util.Calendar;

public class Common {
    public static final String URL_PLANTS = "https://rentit9.herokuapp.com/rest/plants";
    public static final String URL_POS = "https://rentit9.herokuapp.com/rest/pos";

    public static PurchaseOrderResource createDummyOrder() {
        PurchaseOrderLineResource line1 = new PurchaseOrderLineResource();
        line1.setPlantId("1");
        line1.setTotalPrice(100f);
        line1.setStartDate(Calendar.getInstance());
        line1.setEndDate(Calendar.getInstance());

        PurchaseOrderLineResource line2 = new PurchaseOrderLineResource();
        line2.setPlantId("2");
        line2.setTotalPrice(50f);
        line2.setStartDate(Calendar.getInstance());
        line2.setEndDate(Calendar.getInstance());

        PurchaseOrderLineListResource lines = new PurchaseOrderLineListResource();
        lines.purchaseOrders.add(line1);
        lines.purchaseOrders.add(line2);

        PurchaseOrderResource po = new PurchaseOrderResource();
        po.setSiteAddress("derpland 100c, nowhere");
        po.setBuildit("builders inc.");
        po.setPurchaseOrderLines(lines);
        return po;
    }

    public static Client withBasicAuth(Client client) {
        client.addFilter(new HTTPBasicAuthFilter("user", "user"));
        return client;
    }
}
