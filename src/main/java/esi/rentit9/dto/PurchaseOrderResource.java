package esi.rentit9.dto;

import esi.rentit9.domain.OrderStatus;
import esi.rentit9.domain.Plant;
import org.springframework.roo.addon.javabean.RooJavaBean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;

@XmlRootElement(name="purchaseorder")
@XmlAccessorType(XmlAccessType.FIELD)
@RooJavaBean
public class PurchaseOrderResource {

    private Long rentitOrderId;
    private String builditOrderId;
    private String plantId;
    private String buildit;
    private String siteAddress;
    private OrderStatus status;
    private Float total;
    private Calendar startDate;
    private Calendar endDate;

    public Plant getPlantObject() {
        return plantId != null ? Plant.findPlant(Long.parseLong(plantId)) : null;
    }

}