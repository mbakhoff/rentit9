package esi.rentit9.rest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import esi.rentit9.rest.controller.PurchaseOrderRestController.PurchaseOrderResourceList;

public class PurchaseOrderResourceTest {

	// curl -v -X POST -d @sample.xml -H "Content-Type: application/xml" http://localhost:8080/rest/pos

	public static final String URL_POS = "https://rentit9.herokuapp.com/rest/pos";

	@Test
	public void testGetAllOrders() throws Exception {
		Client client = Client.create();
		WebResource webResource = client.resource(URL_POS);
		PurchaseOrderResourceList purchaseOrder = webResource.get(PurchaseOrderResourceList.class);
		assertTrue(purchaseOrder != null);
	}

	@Test
	public void testPostOrder() throws Exception {
	}
}
