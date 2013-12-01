package esi.rentit9.domain;

import org.joda.time.DateMidnight;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class PlantTests {

    final Calendar searchStart = new DateMidnight(2013, 10, 1).toGregorianCalendar();
    final Calendar searchEnd = new DateMidnight(2013, 10, 7).toGregorianCalendar();

    final Calendar orderStart = new DateMidnight(2013, 10, 2).toGregorianCalendar();
    final Calendar orderEnd = new DateMidnight(2013, 10, 5).toGregorianCalendar();

    @Test
    @Transactional
    public void testPlantsBetween() throws Exception {
        Plant derp = new Plant();
        derp.setName("derp");
        derp.setDescription("100% derpomatic!");
        derp.setPrice(9999.9f);
        derp.persist();

        List<Plant> likeQuery = Plant.getPlantsBetween(getPartialName(derp), searchStart, searchEnd);
        Assert.assertTrue(contains(likeQuery, derp));

        List<Plant> beforeOrder = Plant.getPlantsBetween(derp.getName(), searchStart, searchEnd);
        Assert.assertTrue(contains(beforeOrder, derp));

        createApprovedOrder(derp, orderStart, orderEnd);

        List<Plant> afterOrder = Plant.getPlantsBetween(derp.getName(), searchStart, searchEnd);
        Assert.assertFalse(contains(afterOrder, derp));
    }

    private static boolean contains(List<Plant> haystack, Plant needle) {
        for (Plant plant : haystack) {
            if (plant.getId().equals(needle.getId())) {
                return true;
            }
        }
        return false;
    }

    private static String getPartialName(Plant derp) {
        String name = derp.getName();
        return name.substring(1, name.length()-2);
    }

    private static void createApprovedOrder(Plant derp, Calendar orderStart, Calendar orderEnd) {
        PurchaseOrder order = new PurchaseOrder();
        order.setStatus(OrderStatus.APPROVED);
        order.setStartDate(orderStart);
        order.setEndDate(orderEnd);
        order.persist();

        PurchaseOrderLine line = new PurchaseOrderLine();
        line.setPlant(derp);
        line.setPurchaseOrder(order);
        line.persist();
    }
}
