package esi.rentit9.dto;

import esi.rentit9.domain.OrderStatus;
import org.springframework.roo.addon.javabean.RooJavaBean;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@XmlRootElement(name="purchaseorder")
@XmlAccessorType(XmlAccessType.FIELD)
@RooJavaBean
public class PurchaseOrderResource {

    @XmlElementWrapper(name = "plants")
    @XmlElement(name = "plant")
    private List<String> plants = new ArrayList<String>();

    private Long internalId;
    private String senderSideId;
    private String buildit;
    private String siteAddress;
    private OrderStatus status;
    private Float total;
    private Calendar startDate;
    private Calendar endDate;

}
