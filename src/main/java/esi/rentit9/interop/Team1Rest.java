package esi.rentit9.interop;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import esi.rentit9.domain.Invoice;
import esi.rentit9.domain.PurchaseOrder;
import org.joda.time.DateMidnight;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.mail.javamail.JavaMailSender;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

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
    public void sendInvoice(JavaMailSender smtp, Invoice invoice) {
        PurchaseOrder order = invoice.getPurchaseOrder();
        String address = order.getBuildit().getEmail();

        try {
            MimeMessage msg = smtp.createMimeMessage();
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(getEmailSubject(invoice, order));

            MimeBodyPart mbp1 = new MimeBodyPart();
            mbp1.setText("Invoice attached. ");

            MimeBodyPart mbp2 = new MimeBodyPart();
            mbp2.setDataHandler(getInvoiceData(invoice, order));
            mbp2.setFileName("invoice.xml");

            Multipart mp = new MimeMultipart();
            mp.addBodyPart(mbp1);
            mp.addBodyPart(mbp2);
            msg.setContent(mp);

            smtp.send(msg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private DataHandler getInvoiceData(Invoice invoice, PurchaseOrder order) throws JAXBException {
        InvoiceResource res = new InvoiceResource();
        res.invoiceId = invoice.getId();
        res.purchaseOrderId = order.getId();
        res.date = new DateMidnight(invoice.getDueDate()).toString(ISODateTimeFormat.yearMonthDay());
        res.total = BigDecimal.valueOf(order.getTotal());
        return new DataHandler(new InvoiceDataSource<InvoiceResource>(res));
    }

    private String getEmailSubject(Invoice invoice, PurchaseOrder order) {
        return String.format("Invoice %d for order %d from rentit9",
                invoice.getId(),
                order.getId());
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

    @XmlRootElement(name = "Invoice")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class InvoiceResource {
        public long invoiceId;
        public long purchaseOrderId;
        public BigDecimal total;
        public String date;
    }

}
