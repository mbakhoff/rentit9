package esi.rentit9.service;

import esi.rentit9.domain.Invoice;
import esi.rentit9.domain.PurchaseOrder;
import esi.rentit9.interop.BuilditInterop;
import org.joda.time.DateMidnight;
import org.springframework.mail.javamail.JavaMailSender;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Calendar;
import java.util.List;

public class Invoicing {

    public static Invoice createInvoice(PurchaseOrder order) {
        Invoice invoice = new Invoice();
        invoice.setPurchaseOrder(order);
        invoice.setDueDate(new DateMidnight().plusDays(7).toGregorianCalendar());
        invoice.persist();
        return invoice;
    }

    public static List<Invoice> getUnpaidInvoices(){

        Calendar lastAcceptedDate = Calendar.getInstance();
        if (lastAcceptedDate.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
            lastAcceptedDate.add(Calendar.DATE,-3);
        }
        else if (lastAcceptedDate.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY){
            lastAcceptedDate.add(Calendar.DATE,-2);
        }
        else {
            lastAcceptedDate.add(Calendar.DATE,-1);
        }

        List<Invoice> unpaid = Invoice.findInvoicesByDueDateGreaterThan(lastAcceptedDate).getResultList();

        return unpaid;
    }

    public static void sendInvoice(JavaMailSender smtp, Invoice invoice) {
        BuilditInterop interop = invoice.getPurchaseOrder().getBuildit().getInterop();
        interop.sendInvoice(smtp, invoice);
    }

    public static void sendInvoiceEmail(JavaMailSender smtp, String address, DataSource ds, String subject) throws MessagingException {
        MimeMessage msg = smtp.createMimeMessage();
        msg.setRecipients(Message.RecipientType.TO, address);
        msg.setSubject(subject);

        MimeBodyPart mbp1 = new MimeBodyPart();
        mbp1.setText("Invoice attached. ");

        MimeBodyPart mbp2 = new MimeBodyPart();
        mbp2.setDataHandler(new DataHandler(ds));
        mbp2.setHeader("Content-Type", "application/xml");
        mbp2.setFileName("invoice.xml");

        Multipart mp = new MimeMultipart();
        mp.addBodyPart(mbp1);
        mp.addBodyPart(mbp2);
        msg.setContent(mp);

        smtp.send(msg);
    }
}
