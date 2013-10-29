package esi.rentit9.interop;

import esi.rentit9.domain.PurchaseOrder;

public interface BuilditInterop {

    Rest getRest();
    Soap getSoap();

    public static interface Rest {
        void sendAccept(PurchaseOrder order);
    }

    public static interface Soap {
    }
}
