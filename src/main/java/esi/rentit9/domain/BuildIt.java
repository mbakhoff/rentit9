package esi.rentit9.domain;
import esi.rentit9.interop.BuilditInterop;
import esi.rentit9.interop.InteropImplementation;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findBuildItsByNameEquals" })
public class BuildIt {

    private String name;

    private String email;

    private InteropImplementation provider;

    public BuilditInterop getInterop() {
        if (provider == null) {
            System.err.println("no provider defined for buildit " + getName());
            return InteropImplementation.Dummy.getImpl();
        }
        return provider.getImpl();
    }

}
