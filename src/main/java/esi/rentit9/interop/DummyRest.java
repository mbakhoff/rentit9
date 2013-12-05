package esi.rentit9.interop;

import esi.rentit9.domain.Invoice;
import esi.rentit9.domain.PurchaseOrder;

public class DummyRest implements BuilditInterop.Rest {

    @Override
    public void sendAccept(PurchaseOrder order) {
        System.out.println(getClass().getCanonicalName() + " sending po accept");
    }
    @Override
    public void sendReject(PurchaseOrder order) {
        System.out.println(getClass().getCanonicalName() + " sending po reject");
    }

    @Override
    public void sendInvoice(Invoice invoice) {
        System.out.println("dummy sending invoice");
    }
}
