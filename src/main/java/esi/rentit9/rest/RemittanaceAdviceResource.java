package esi.rentit9.rest;

import esi.rentit9.rest.util.ExtendedResourceSupport;
import org.springframework.roo.addon.javabean.RooJavaBean;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;

@RooJavaBean
@XmlRootElement(name="remittanceadvice")
public class RemittanaceAdviceResource extends ExtendedResourceSupport {
	
	private Long invoiceId;
	private Calendar payDay;
	
}
