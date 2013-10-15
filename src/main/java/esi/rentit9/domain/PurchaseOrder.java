package esi.rentit9.domain;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class PurchaseOrder {

    /**
     */
    @ManyToOne
    private BuildIt buildit;

    /**
     */
    private Boolean approved;

    /**
     */
    private String siteAddress;

	@OneToMany
	private List<PurchaseOrderLine> lines;

}
