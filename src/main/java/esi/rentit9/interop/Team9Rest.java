package esi.rentit9.interop;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import esi.rentit9.domain.PurchaseOrder;
import esi.rentit9.soap.PurchaseOrderAssembler;

public class Team9Rest implements BuilditInterop.Rest {

    public static final String BUILDIT_POS = "https://buildit9.herokuapp.com/rest/pos";

    private final PurchaseOrderAssembler assembler;

    public Team9Rest() {
        assembler = new PurchaseOrderAssembler();
    }

    @Override
    public void sendAccept(PurchaseOrder order) {
        ClientResponse createRequest = Client.create()
                .resource(getConfirmUrl(order))
                .post(ClientResponse.class);

        int status = createRequest.getStatus();
        if (status != ClientResponse.Status.OK.getStatusCode()) {
            throw new InteropException();
        }
    }

    private static String getConfirmUrl(PurchaseOrder order) {
        return String.format("%s/%d/confirm", BUILDIT_POS, order.getId());
    }

}
