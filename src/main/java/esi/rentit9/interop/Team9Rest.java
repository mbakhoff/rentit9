package esi.rentit9.interop;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import esi.rentit9.domain.Invoice;
import esi.rentit9.domain.PurchaseOrder;

public class Team9Rest implements BuilditInterop.Rest {

    public static final String BUILDIT_POS = "https://buildit9.herokuapp.com/rest/pos";

    @Override
    public void sendAccept(PurchaseOrder order) {
        ClientResponse response = getClient()
                .resource(getConfirmUrl(order))
                .post(ClientResponse.class);

        int status = response.getStatus();
        if (status != ClientResponse.Status.OK.getStatusCode()) {
            throw new RemoteHostException(response);
        }
    }

    @Override
    public void sendReject(PurchaseOrder order) {
        ClientResponse response = getClient()
                .resource(getConfirmUrl(order))
                .delete(ClientResponse.class);

        int status = response.getStatus();
        if (status != ClientResponse.Status.OK.getStatusCode()) {
            throw new RemoteHostException(response);
        }
    }

    @Override
    public void sendInvoice(Invoice invoice) {
        throw new UnsupportedOperationException();
    }

    private static Client getClient() {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("rentit", "rentit"));
        return client;
    }

    private static String getConfirmUrl(PurchaseOrder order) {
        return String.format("%s/%d/confirm", BUILDIT_POS, order.getId());
    }

}
