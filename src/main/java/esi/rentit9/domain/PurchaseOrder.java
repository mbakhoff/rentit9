package esi.rentit9.domain;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

@RooJavaBean
@RooJpaActiveRecord
public class PurchaseOrder {

    @ManyToOne
    private BuildIt buildit;

    @ManyToOne
    private Plant plant;

    private OrderStatus status;

    private Float total;

    private String siteAddress;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Calendar startDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Calendar endDate;

    private String senderSideId;

    @Override
    public String toString() {
        return String.format("order %d by %s", getId(), buildit.getName());
    }
}
