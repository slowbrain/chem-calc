package pl.edu.pjwstk.chemcalc.code.datastore;

import java.util.List;

public class ComplexObject extends SBAObject implements IComplexObject {
    private List<OID> childOIDs;

    /**
     * @param aOid
     * @param aName
     * @param aOIDs
     */
    public ComplexObject(OID aOid, String aName, List<OID> aOIDs) {
        super(aOid, aName);
        childOIDs = aOIDs;
    }

    public List<OID> getChildOIDs() {
        return childOIDs;
    }

    public String toString() {
        return name;
    }
}