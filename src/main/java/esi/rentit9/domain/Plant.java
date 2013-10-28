package esi.rentit9.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.Query;
import java.util.Calendar;
import java.util.List;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Plant {

    public static final String QUERY_PLANTS_BETWEEN =
            "";

    /**
     */
    private String name;

    /**
     */
    private String description;

    /**
     */
    private Float price;


    public static List<Plant> getPlantsBetween(String nameLike, Calendar startDate, Calendar endDate){
        //TypedQuery<PurchaseOrderLine> query =
        //        entityManager().createQuery(QUERY_ORDER_LINES, PurchaseOrderLine.class);
        //query.setParameter("order", this);
        //TODO: Query data.
        return null;// query.getResultList();
    }
}
