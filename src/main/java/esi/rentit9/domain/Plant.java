package esi.rentit9.domain;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.List;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Plant {

    private static final String QUERY_PLANTS_BETWEEN =
            "FROM Plant AS plant " +
                    "WHERE plant.name LIKE :name AND " +
                    "NOT EXISTS (FROM PurchaseOrderLine AS line WHERE line.plant = plant AND line.purchaseOrder.status = :postatus AND line.endDate > :start AND line.startDate < :end)";

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
        TypedQuery<Plant> query = entityManager().createQuery(QUERY_PLANTS_BETWEEN, Plant.class);
        query.setParameter("name", '%'+nameLike+'%');
        query.setParameter("start", startDate);
        query.setParameter("end", endDate);
        query.setParameter("postatus", OrderStatus.Approved);
        return query.getResultList();
    }
}
