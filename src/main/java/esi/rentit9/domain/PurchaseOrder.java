package esi.rentit9.domain;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Set;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class PurchaseOrder {

	/**
     */
    @ManyToOne
    private BuildIt buildit;

    /**
     */
    private OrderStatus status;

    /**
     */
    private Float total;

    /**
     */
    private String siteAddress;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Calendar startDate;

    /**
     */
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Calendar endDate;

    private String senderSideId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchaseOrder")
    private Set<PurchaseOrderLine> lines;

}
