package esi.rentit9.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import esi.rentit9.dto.PurchaseOrderResource;

import java.util.Arrays;
import java.util.Calendar;

public class Common {
    public static final String URL_PLANTS = "https://rentit9.herokuapp.com/rest/plants";
    public static final String URL_POS = "https://rentit9.herokuapp.com/rest/pos";

    public static PurchaseOrderResource createDummyOrder() {
        PurchaseOrderResource po = new PurchaseOrderResource();
        po.setSiteAddress("derpland 100c, nowhere");
        po.setBuildit("builders inc.");
        po.setPlants(Arrays.asList("1", "2"));
        po.setStartDate(Calendar.getInstance());
        po.setEndDate(Calendar.getInstance());
        return po;
    }

    public static Client withBasicAuth(Client client) {
        client.addFilter(new HTTPBasicAuthFilter("admin", "admin"));
        return client;
    }
}
