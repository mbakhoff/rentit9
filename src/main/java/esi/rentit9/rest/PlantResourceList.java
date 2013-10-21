package esi.rentit9.rest;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="plants")
public class PlantResourceList {

	public List<PlantResource> plant;

}
