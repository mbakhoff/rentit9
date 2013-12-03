package esi.rentit9.web;

import esi.rentit9.domain.Plant;
import esi.rentit9.domain.PurchaseOrder;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.util.Calendar;

public final class PlantToDeliver {

    public final Long orderId;
    public final Long plantId;
    public final String plantName;
    public final String deliveryDate;

    public PlantToDeliver(PurchaseOrder order) {
        Plant plant = order.getPlant();
        this.orderId = order.getId();
        this.plantId = plant.getId();
        this.plantName = plant.getName();
        this.deliveryDate = toDateString(order.getStartDate());
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
