package esi.rentit9.rest;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertTrue;

public class TestPlantResource {

	// @Test TODO: fixme
	public void testResources() throws Exception {
		Client client = Client.create();
		WebResource webResource =
				client.resource("http://localhost:8080/rest/plant");
		PlantResource newPlantResource = new PlantResource();
		newPlantResource.setDescription("Dodge 2013");
		newPlantResource.setName("Truck");
		newPlantResource.setPrice(200.0f);
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML).post(ClientResponse.class, newPlantResource);
		assertTrue(response.getStatus() == ClientResponse.Status.CREATED.getStatusCode());
	}

}
