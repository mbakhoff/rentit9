package esi.rentit9.rest;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlantResourceTest {

	public static final String URL_PLANTS = "https://rentit9.herokuapp.com/rest/plants";

    @Test
	public void testGetPlants() throws Exception {
		Client client = Client.create();
		WebResource webResource = client.resource(URL_PLANTS);
		PlantResourceList plants = webResource.get(PlantResourceList.class);
		assertTrue(plants != null);
	}

	@Test
	public void testResources() throws Exception {
		Client client = Client.create();
		WebResource webResource = client.resource(URL_PLANTS);
		PlantResource newPlantResource = new PlantResource();
		newPlantResource.setDescription("Dodge 2013");
		newPlantResource.setName("Truck");
		newPlantResource.setPrice(200.0f);
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML).post(ClientResponse.class, newPlantResource);
		assertTrue(response.getStatus() == ClientResponse.Status.CREATED.getStatusCode());
	}

    @Test
    public void testGetPlantById() throws Exception {
        Client client = Client.create();
        WebResource webResource = client.resource(URL_PLANTS);
        PlantResourceList plants = webResource.get(PlantResourceList.class);

        assertTrue(plants!=null);

        PlantResource plant = plants.getPlant().get(0);
        String requestUrl = URL_PLANTS +"/"+plant.getId();
        webResource = client.resource(requestUrl);
        PlantResource plantById = webResource.get(PlantResource.class);

        assertEquals(plantById.getId(), plant.getId());
    }

}
