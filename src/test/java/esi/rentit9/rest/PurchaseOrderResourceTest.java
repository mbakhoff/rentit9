package esi.rentit9.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import esi.rentit9.rest.controller.PurchaseOrderRestController.PurchaseOrderResourceList;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertTrue;


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
		PurchaseOrderResource po = createDummyOrder();
		Client client = Client.create();
		WebResource webResource = client.resource(URL_POS);
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML).post(ClientResponse.class, po);
		assertTrue(response.getStatus() == ClientResponse.Status.CREATED.getStatusCode());
	}
	
	@Test
	public void testModifyPostOrder() throws Exception {
		PurchaseOrderResource po = createDummyOrder();
		Client client = Client.create();
		WebResource webResource = client.resource(URL_POS);
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML).post(ClientResponse.class, po);
		List<String> id = response.getHeaders().get("RentItId");
		
		PurchaseOrderResource po2 = createDummyOrder();
		po2.setSiteAddress("NewModifiedDerpland 404");
		
        String requestUrl = URL_POS +"/"+id.get(0);
        webResource = client.resource(requestUrl);
        ClientResponse response2 = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML).put(ClientResponse.class, po2);
        
		assertTrue(response2.getStatus() == ClientResponse.Status.OK.getStatusCode());
	}
	
	@Test
	public void testCancelOrder() throws Exception {
		PurchaseOrderResource po = createDummyOrder();
		Client client = Client.create();
		WebResource webResource = client.resource(URL_POS);
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML).post(ClientResponse.class, po);
		List<String> id = response.getHeaders().get("RentItId");
		
        String requestUrl = URL_POS +"/"+id.get(0);
        webResource = client.resource(requestUrl);
        ClientResponse response2 = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML).delete(ClientResponse.class);
		assertTrue(response2.getStatus() == ClientResponse.Status.OK.getStatusCode());
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
