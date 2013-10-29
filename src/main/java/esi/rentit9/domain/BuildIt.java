package esi.rentit9.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.TypedQuery;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findBuildItsByNameEquals" })
//@RooJpaActiveRecord(finders = { "findRentItsByNameEquals" })
public class BuildIt {

    public static final String QUERY_BY_URL = "FROM BuildIt AS buildIt WHERE buildIt.url = :url";

    /**
     */
    private String name;

    /**
     */
    private String url;

    public static BuildIt getByUrl(String url) {
        TypedQuery<BuildIt> query = entityManager().createQuery(QUERY_BY_URL, BuildIt.class);
        query.setParameter("url", url);
        return query.getSingleResult();
    }
}
