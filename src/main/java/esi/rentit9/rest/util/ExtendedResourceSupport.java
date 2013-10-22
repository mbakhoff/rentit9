package esi.rentit9.rest.util;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.hateoas.Link;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

@XmlTransient
public class ExtendedResourceSupport {

    @XmlElement(name = "link", namespace = Link.ATOM_NAMESPACE)
    @JsonProperty("links")
    private final List<ExtendedLink> extendedLinks;

    public ExtendedResourceSupport() {
        this.extendedLinks = new ArrayList<ExtendedLink>();
    }

    public void add(ExtendedLink link) {
        extendedLinks.add(link);
    }

    public List<ExtendedLink> getExtendedLinks() {
        return extendedLinks;
    }

    public ExtendedLink getByRel(String rel) {
        for (ExtendedLink link : extendedLinks) {
            if (link.getRel().equals(rel)) {
                return link;
            }
        }
        return null;
    }

}
