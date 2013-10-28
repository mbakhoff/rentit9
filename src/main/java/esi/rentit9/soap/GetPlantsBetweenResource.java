package esi.rentit9.soap;

import org.springframework.roo.addon.javabean.RooJavaBean;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;

@RooJavaBean
@XmlRootElement(name = "getplantsbetweenrequest")
public class GetPlantsBetweenResource {
    private String nameLike;
    private Calendar startDate;
    private Calendar endDate;
}
