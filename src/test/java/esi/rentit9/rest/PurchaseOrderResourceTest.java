package esi.rentit9.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import esi.rentit9.rest.controller.PurchaseOrderRestController.PurchaseOrderResourceList;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.List;

import static esi.rentit9.rest.Common.withBasicAuth;
import static org.junit.Assert.assertTrue;


public class PurchaseOrderResourceTest {

    private final Client client;

    public PurchaseOrderResourceTest() {
        client = withBasicAuth(Client.create());
    }

    @Test
	public void testGetAllOrders() throws Exception {
		WebResource webResource = client.resource(Common.URL_POS);
		PurchaseOrderResourceList purchaseOrder = webResource.get(PurchaseOrderResourceList.class);
		assertTrue(purchaseOrder != null);
	}

	@Test
	public void testPostOrder() throws Exception {
		PurchaseOrderResource po = Common.createDummyOrder();
        WebResource webResource = client.resource(Common.URL_POS);
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML).post(ClientResponse.class, po);
		assertTrue(response.getStatus() == ClientResponse.Status.CREATED.getStatusCode());
	}
	
	@Test
	public void testModifyPostOrder() throws Exception {
		PurchaseOrderResource po = Common.createDummyOrder();
		WebResource webResource = client.resource(Common.URL_POS);
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML).post(ClientResponse.class, po);
		List<String> id = response.getHeaders().get("RentItId");
		
		PurchaseOrderResource po2 = Common.createDummyOrder();
		po2.setSiteAddress("NewModifiedDerpland 404");
		
        String requestUrl = Common.URL_POS +"/"+id.get(0);
        webResource = client.resource(requestUrl);
        ClientResponse response2 = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML).put(ClientResponse.class, po2);
        
		assertTrue(response2.getStatus() == ClientResponse.Status.OK.getStatusCode());
	}
	
	@Test
	public void testCancelOrder() throws Exception {
		PurchaseOrderResource po = Common.createDummyOrder();
		WebResource webResource = client.resource(Common.URL_POS);
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML).post(ClientResponse.class, po);
		List<String> id = response.getHeaders().get("RentItId");
		
        String requestUrl = Common.URL_POS +"/"+id.get(0);
        webResource = client.resource(requestUrl);
        ClientResponse response2 = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML).delete(ClientResponse.class);
		assertTrue(response2.getStatus() == ClientResponse.Status.OK.getStatusCode());
	}

}
