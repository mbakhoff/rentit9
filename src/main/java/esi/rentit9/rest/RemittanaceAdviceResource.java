package esi.rentit9.rest;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.roo.addon.javabean.RooJavaBean;

import esi.rentit9.domain.Invoice;
import esi.rentit9.rest.util.ExtendedResourceSupport;

@RooJavaBean
@XmlRootElement(name="remittanceadvice")
public class RemittanaceAdviceResource extends ExtendedResourceSupport {
	
	private Invoice invoice;
	private Calendar payDay;
	
}
