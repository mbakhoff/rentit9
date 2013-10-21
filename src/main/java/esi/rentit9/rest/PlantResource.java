package esi.rentit9.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="plant")
public class PlantResource {

    public Long id;

	public String name;

	public String description;

	public Float price;

}
