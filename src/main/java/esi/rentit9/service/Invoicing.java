package esi.rentit9.service;

import esi.rentit9.domain.Invoice;
import esi.rentit9.domain.PurchaseOrder;
import esi.rentit9.interop.InteropImplementation;
import org.joda.time.DateMidnight;
import org.springframework.mail.javamail.JavaMailSender;

public class Invoicing {

    public static Invoice createInvoice(PurchaseOrder order) {
        Invoice invoice = new Invoice();
        invoice.setPurchaseOrder(order);
        invoice.setDueDate(new DateMidnight().plusDays(7).toGregorianCalendar());
        invoice.persist();
        return invoice;
    }

    public static void sendInvoice(JavaMailSender smtp, Invoice invoice) {
        InteropImplementation interop = invoice.getPurchaseOrder().getBuildit().getProvider();
        if (interop != null) {
            interop.getRest().sendInvoice(smtp, invoice);
        } else {
            throw new RuntimeException("failed to find interop for invoice " + invoice.getId());
        }
    }

}
