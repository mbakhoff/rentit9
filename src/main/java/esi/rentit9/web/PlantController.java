package esi.rentit9.web;

import esi.rentit9.domain.Plant;
import esi.rentit9.domain.PurchaseOrder;
import org.joda.time.DateMidnight;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/plants")
@Controller
@RooWebScaffold(path = "plants", formBackingObject = Plant.class)
public class PlantController {

    @RequestMapping(value = "/deliverables", produces = "text/html")
    public String deliverables(Model uiModel, @RequestParam(value = "dateOffset", required = false) Integer dateOffset) {
        DateMidnight deliveryDate = calculateDate(dateOffset);
        addDeliverables(uiModel, Plant.getPlantsToDeliver(deliveryDate));
        uiModel.addAttribute("date", deliveryDate.toString(ISODateTimeFormat.yearMonthDay()));
        uiModel.addAttribute("nextPage", dateOffset == null ? 1 : dateOffset+1);
        uiModel.addAttribute("prevPage", dateOffset == null ? -1 : dateOffset-1);
        return "plants/deliverables";
    }

    private static void addDeliverables(Model uiModel, List<PurchaseOrder> ordersForDate) {
        List<PlantToDeliver> deliveries = new ArrayList<PlantToDeliver>();
        for (PurchaseOrder orderLine : ordersForDate) {
            deliveries.add(new PlantToDeliver(orderLine));
        }
        uiModel.addAttribute("plants", deliveries);
    }

    private static DateMidnight calculateDate(Integer dateOffset) {
        DateMidnight date = new DateMidnight();
        if (dateOffset != null) {
            return date.plusDays(dateOffset);
        }
        return date;
    }

}
