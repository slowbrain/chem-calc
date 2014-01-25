package pl.edu.pjwstk.chemcalc.code.datastore;

public class BooleanObject extends SimpleObject<Boolean> implements
        IBooleanObject {
    /**
     * @param aOid
     * @param aName
     * @param aValue
     */
    public BooleanObject(OID aOid, String aName, Boolean aValue) {
        super(aOid, aName, aValue);
    }

    public String toString() {
        return value.toString();
    }
}