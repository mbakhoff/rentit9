package esi.rentit9.interop;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import esi.rentit9.domain.Invoice;
import esi.rentit9.domain.PurchaseOrder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

public class Team1Rest implements BuilditInterop.Rest {

    private static final String BUILDIT_URL = "https://buildit1.herokuapp.com/rest";

    @Override
    public void sendAccept(PurchaseOrder order) {
        // buildit1 doesn't need a confirmation
    }

    @Override
    public void sendReject(PurchaseOrder order) {
        // TODO: figure out how to differentiate between these:
        // reject modification: POST "/PlantHireRequests/acceptbyrentit"
        // reject extension: POST "/PlantHireRequests/acceptextensionbyrentit"

        RejectPOResource rejectResource = new RejectPOResource();
        rejectResource.poid = Long.parseLong(order.getSenderSideId());
        rejectResource.commentt = "rejected by rentit9";

        WebResource webResource = getClient().resource(BUILDIT_URL+"/PlantHireRequests/acceptbyrentit");
        ClientResponse request = webResource.post(ClientResponse.class, rejectResource);

        if (request.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new RemoteHostException(request);
        }
    }

    @Override
    public void sendInvoice(Invoice invoice) {
        String address = invoice.getPurchaseOrder().getBuildit().getEmail();
        // TODO: email the invoice
    }

    private static Client getClient() {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("customer", "customer"));
        return client;
    }

    @XmlRootElement(name="RejectPO")
    @XmlAccessorType(XmlAccessType.FIELD)
    public class RejectPOResource {
        public Long poid;
        public String commentt;
    }

}
