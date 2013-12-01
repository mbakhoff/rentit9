package esi.rentit9.web;

import esi.rentit9.domain.Plant;
import esi.rentit9.domain.PurchaseOrderLine;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.util.Calendar;

public final class PlantToDeliver {

    public final Long orderId;
    public final Long plantId;
    public final String plantName;
    public final String deliveryDate;

    public PlantToDeliver(PurchaseOrderLine orderLine) {
        Plant plant = orderLine.getPlant();
        this.orderId = orderLine.getPurchaseOrder().getId();
        this.plantId = plant.getId();
        this.plantName = plant.getName();
        this.deliveryDate = toDateString(orderLine.getStartDate());
    }

    private static String toDateString(Calendar date) {
        return new DateTime(date).toString(ISODateTimeFormat.yearMonthDay());
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getPlantId() {
        return plantId;
    }

    public String getPlantName() {
        return plantName;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }
}
