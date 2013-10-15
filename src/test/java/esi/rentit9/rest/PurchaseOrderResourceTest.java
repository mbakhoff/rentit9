package esi.rentit9.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import java.util.Calendar;

import static org.junit.Assert.assertTrue;

public class PurchaseOrderResourceTest {

	// curl -v -X POST -d @sample.xml -H "Content-Type: application/xml" http://localhost:8080/rest/pos

	public static final String URL_POS = "https://rentit9.herokuapp.com/rest/pos";

	@Test
	public void testGetAllOrders() throws Exception {
	}

	@Test
	public void testPostOrder() throws Exception {
		PurchaseOrderResource po = createDummyOrder();
		Client client = Client.create();
		WebResource webResource = client.resource(URL_POS);
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML).post(ClientResponse.class, po);
		assertTrue(response.getStatus() == ClientResponse.Status.CREATED.getStatusCode());
	}

	private static PurchaseOrderResource createDummyOrder() {
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

		PurchaseOrderLineResourceList lines = new PurchaseOrderLineResourceList();
		lines.purchaseOrders.add(line1);
		lines.purchaseOrders.add(line2);

		PurchaseOrderResource po = new PurchaseOrderResource();
		po.setSiteAddress("derpland 100c, nowhere");
		po.setBuildit("builders inc.");
		po.setPurchaseOrderLines(lines);
		return po;
	}
}
