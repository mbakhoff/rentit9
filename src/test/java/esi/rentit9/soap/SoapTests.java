package esi.rentit9.soap;

import esi.rentit9.domain.OrderStatus;
import esi.rentit9.soap.web.PlantSoapService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext-*.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SoapTests {

    private PlantSoapService plantSoapService = new PlantSoapService();


    @Test
    public void CanCreatePurchaseOrder(){
        PurchaseOrderResource resource=new PurchaseOrderResource();
        resource.setBuildit("Test");
        resource.setSiteAddress("Test");
        resource.setStatus(OrderStatus.CREATED);

        PurchaseOrderLineResource line = new PurchaseOrderLineResource();
        Calendar startDate = Calendar.getInstance();
        startDate.set(2010,10,10);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2010,10,11);

        line.setStartDate(startDate);
        line.setEndDate(endDate);

        List<PurchaseOrderLineResource> purchaseOrderLineResourceList = new ArrayList<PurchaseOrderLineResource>();
        purchaseOrderLineResourceList.add(line);
        //resource.setPurchaseOrderLines(purchaseOrderLineResourceList);

        PurchaseOrderResource result = plantSoapService.createPurchaseOrder(resource);

    }
}
