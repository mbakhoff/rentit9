package esi.rentit9.domain;

import org.joda.time.DateMidnight;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.List;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Plant {

    private static final String QUERY_PLANTS_BETWEEN =
            "FROM Plant AS plant WHERE " +
                    "plant.name LIKE :name AND " +
                    "NOT EXISTS (FROM PurchaseOrder AS order WHERE " +
                    "order.plant = plant AND " +
                    "order.status = :postatus AND " +
                    "order.endDate > :start AND " +
                    "order.startDate < :end)";

    private static final String QUERY_ORDERS_FOR_DELIVERY =
            "FROM PurchaseOrder AS order WHERE " +
                    "order.plant = plant AND " +
                    "order.status = :postatus AND " +
                    "order.startDate = :date";

    private String name;

    private String description;

    private Float price;

    public static List<Plant> getPlantsBetween(String nameLike, Calendar startDate, Calendar endDate){
        TypedQuery<Plant> query = entityManager().createQuery(QUERY_PLANTS_BETWEEN, Plant.class);
        query.setParameter("name", '%'+nameLike+'%');
        query.setParameter("start", startDate);
        query.setParameter("end", endDate);
        query.setParameter("postatus", OrderStatus.APPROVED);
        return query.getResultList();
    }

    public static List<PurchaseOrder> getPlantsToDeliver(DateMidnight date) {
        TypedQuery<PurchaseOrder> query = entityManager().createQuery(
                QUERY_ORDERS_FOR_DELIVERY,
                PurchaseOrder.class);
        query.setParameter("date", date.toGregorianCalendar(), TemporalType.DATE);
        query.setParameter("postatus", OrderStatus.APPROVED);
        return query.getResultList();
    }
}
