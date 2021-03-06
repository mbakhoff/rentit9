package esi.rentit9.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import esi.rentit9.dto.PurchaseOrderResource;
import esi.rentit9.dto.PurchaseOrderResourceList;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import javax.ws.rs.core.MediaType;

import static esi.rentit9.rest.Common.withBasicAuth;
import static org.junit.Assert.*;


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
				.accept(MediaType.APPLICATION_XML)
                .post(ClientResponse.class, po);
		assertTrue(response.getStatus() == ClientResponse.Status.CREATED.getStatusCode());

        PurchaseOrderResource returned = response.getEntity(PurchaseOrderResource.class);
        assertEquals(po.getStartDate().getTime(), returned.getStartDate().getTime());
        assertEquals(po.getEndDate().getTime(), returned.getEndDate().getTime());
	}
	
	@Test
	public void testModifyPostOrder() throws Exception {
		PurchaseOrderResource po = Common.createDummyOrder();
		WebResource webResource = client.resource(Common.URL_POS);
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
                .post(ClientResponse.class, po);
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		String id = response.getHeaders().getFirst("EntityId");
		
		PurchaseOrderResource po2 = Common.createDummyOrder();
		po2.setSiteAddress("NewModifiedDerpland 404");

        String requestUrl = Common.URL_POS +"/"+id;
        webResource = client.resource(requestUrl);
        ClientResponse response2 = webResource.type(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML)
                .put(ClientResponse.class, po2);

        assertTrue(response2.getStatus() == ClientResponse.Status.OK.getStatusCode());
	}
	
	@Test
	public void testCancelOrder() throws Exception {
		PurchaseOrderResource po = Common.createDummyOrder();
		WebResource webResource = client.resource(Common.URL_POS);
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
                .post(ClientResponse.class, po);
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		String id = response.getHeaders().getFirst("EntityId");

        String requestUrl = Common.URL_POS +"/"+id;
        webResource = client.resource(requestUrl);
        ClientResponse response2 = webResource.type(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML).delete(ClientResponse.class);
        assertTrue(response2.getStatus() == ClientResponse.Status.OK.getStatusCode());
	}

    @Test
    public void testGetOrders() throws Exception {
        WebResource webResource = client.resource(Common.URL_POS);
        PurchaseOrderResourceList response = webResource.type(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML)
                .get(PurchaseOrderResourceList.class);
        assertNotNull(response);
    }
}
