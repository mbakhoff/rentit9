package esi.rentit9.domain;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

@RooJavaBean
@RooJpaActiveRecord(finders = { "findInvoicesByDueDateGreaterThan" })
public class Invoice {

    /**
     */
    @ManyToOne
    private PurchaseOrder purchaseOrder;

    /**
     */
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Calendar dueDate;

    @Override
    public String toString() {
        return String.format("invoice %d for order %d", getId(), purchaseOrder.getId());
    }
}
