package esi.rentit9.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "plants")
public class PlantResourceList {

    @XmlElement(name = "plant")
    public List<PlantResource> plants;

    @SuppressWarnings("UnusedDeclaration")
    public PlantResourceList() {
        plants = new ArrayList<PlantResource>();
    }

    public PlantResourceList(List<PlantResource> purchaseorder) {
        this.plants = purchaseorder;
    }
}
