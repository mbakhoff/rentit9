package esi.rentit9.domain;
import esi.rentit9.interop.InteropImplementation;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findBuildItsByNameEquals" })
public class BuildIt {

    private String name;

    private InteropImplementation provider;

    public static BuildIt getOrCreate(String name) {
        try {
            return findBuildItsByNameEquals(name).getSingleResult();
        } catch (DataRetrievalFailureException notFound) {
            BuildIt match = new BuildIt();
            match.setName(name);
            match.persist();
            return match;
        }
    }
}
