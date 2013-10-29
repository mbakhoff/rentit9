package esi.rentit9.interop;

import esi.rentit9.domain.PurchaseOrder;

public class DummyRest implements BuilditInterop.Rest {

    @Override
    public void sendAccept(PurchaseOrder order) {
        System.out.println(getClass().getCanonicalName() + " sending po accept");
    }
}
