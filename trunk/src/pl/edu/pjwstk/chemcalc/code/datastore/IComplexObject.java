package pl.edu.pjwstk.chemcalc.code.datastore;

import java.util.List;

public interface IComplexObject extends ISBAObject {
    public List<OID> getChildOIDs();
}