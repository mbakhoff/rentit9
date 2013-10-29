package esi.rentit9.soap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="plants")
public class PlantResourceList {
    @XmlElement(name="plant")
    public List<PlantResource> plants = new ArrayList<PlantResource>();
}
