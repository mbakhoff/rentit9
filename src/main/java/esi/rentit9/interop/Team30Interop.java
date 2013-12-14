package esi.rentit9.interop;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import esi.rentit9.domain.Invoice;
import esi.rentit9.domain.PurchaseOrder;
import esi.rentit9.service.InvoiceDataSource;
import esi.rentit9.service.Invoicing;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

public class Team30Interop implements BuilditInterop {

    public static final String BUILDIT_POS = "https://buildit30.herokuapp.com/rest/requests";

    @Override
    public void sendAccept(PurchaseOrder order) {
        ClientResponse response = getClient()
                .resource(String.format("%s/approve/%s", BUILDIT_POS, order.getSenderSideId()))
                .get(ClientResponse.class);

        int status = response.getStatus();
        if (status != ClientResponse.Status.OK.getStatusCode()) {
            throw new RemoteHostException(response);
        }
    }

    @Override
    public void sendReject(PurchaseOrder order) {
        RejectBean res = new RejectBean();
        res.id = Long.parseLong(order.getSenderSideId());
        res.comment = "rejected by rentit9";

        ClientResponse response = getClient()
                .resource(String.format("%s/reject", BUILDIT_POS))
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, res);

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
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email to buildit30", e);
        } catch (JAXBException e) {
            throw new RuntimeException("Failed to marshall invoice " + invoice.getId(), e);
        }
    }

    private InvoiceResource getInvoiceResource(Invoice invoice, PurchaseOrder order) {
        InvoiceResource res = new InvoiceResource();
        res.date = invoice.getDueDate().getTime();
        res.invoiceId = invoice.getId();
        res.poId = order.getId();
        res.total = order.getTotal().doubleValue();
        res.requestId = Long.parseLong(order.getSenderSideId());
        return res;
    }

    private static Client getClient() {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("rentit", "rentit"));
        return client;
    }

    @XmlRootElement(name = "InvoiceResource")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class InvoiceResource {
        public Long invoiceId;
        public Date date;
        public Double total;
        public Long requestId;
        public Long poId;
    }

    @XmlRootElement(name = "RejectBean")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class RejectBean {
        public String comment;
        public Long id;
    }

}
