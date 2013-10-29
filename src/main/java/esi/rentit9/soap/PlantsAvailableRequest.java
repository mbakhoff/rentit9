package esi.rentit9.soap;

import org.springframework.roo.addon.javabean.RooJavaBean;

import java.util.Calendar;

@RooJavaBean
public class PlantsAvailableRequest {
    private String nameLike;
    private Calendar startDate;
    private Calendar endDate;
}
