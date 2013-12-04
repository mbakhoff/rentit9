package esi.rentit9.dto;

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

    private String rentitOrderId;
    private String builditOrderId;
    private String plantId;
    private String buildit;
    private String rentit;
    private String siteAddress;
    private String status;
    private Float total;
    private Calendar startDate;
    private Calendar endDate;

    public Plant getPlantObject() {
        return plantId != null ? Plant.findPlant(Long.parseLong(plantId)) : null;
    }

}
