package esi.rentit9.interop;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import esi.rentit9.domain.Invoice;
import esi.rentit9.domain.PurchaseOrder;
import esi.rentit9.service.InvoiceDataSource;
import esi.rentit9.service.Invoicing;
import org.springframework.mail.javamail.JavaMailSender;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

public class Team9Rest implements BuilditInterop.Rest {

    public static final String BUILDIT_POS = "https://buildit9.herokuapp.com/rest/pos";

    @Override
    public void sendAccept(PurchaseOrder order) {
        ClientResponse response = getClient()
                .resource(String.format("%s/%s/rentitconfirm", BUILDIT_POS, order.getSenderSideId()))
                .post(ClientResponse.class);

        int status = response.getStatus();
        if (status != ClientResponse.Status.OK.getStatusCode()) {
            throw new RemoteHostException(response);
        }
    }

    @Override
    public void sendReject(PurchaseOrder order) {
        ClientResponse response = getClient()
                .resource(String.format("%s/%s/rentitreject", BUILDIT_POS, order.getSenderSideId()))
                .post(ClientResponse.class);

        int status = response.getStatus();
        if (status != ClientResponse.Status.OK.getStatusCode()) {
            throw new RemoteHostException(response);
        }
    }

    @Override
    public void sendInvoice(JavaMailSender smtp, Invoice invoice) {
        PurchaseOrder order = invoice.getPurchaseOrder();
        String address = order.getBuildit().getEmail();
        try {
            InvoiceResource resource = getInvoiceResource(invoice, order);
            InvoiceDataSource<InvoiceResource> ds = new InvoiceDataSource<InvoiceResource>(resource);
            Invoicing.sendInvoiceEmail(smtp, address, ds, "invoice from rentit9");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private InvoiceResource getInvoiceResource(Invoice invoice, PurchaseOrder order) {
        InvoiceResource res = new InvoiceResource();
        res.id = invoice.getId();
        res.po = Long.parseLong(order.getSenderSideId());
        res.total = order.getTotal();
        return res;
    }

    private static Client getClient() {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("rentit9", "rentit9"));
        return client;
    }

    @XmlRootElement(name = "invoice")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class InvoiceResource {

        public Long id;
        public Long po;
        public Float total;

    }

}
