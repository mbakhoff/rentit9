package esi.rentit9.interop;

import esi.rentit9.domain.Invoice;
import esi.rentit9.domain.PurchaseOrder;
import org.springframework.mail.javamail.JavaMailSender;

public interface BuilditInterop {

    void sendAccept(PurchaseOrder order);
    void sendReject(PurchaseOrder order);
    void sendInvoice(JavaMailSender smtp, Invoice invoice);

}
