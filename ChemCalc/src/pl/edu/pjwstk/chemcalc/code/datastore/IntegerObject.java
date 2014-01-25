package pl.edu.pjwstk.chemcalc.code.datastore;

public class IntegerObject extends SimpleObject<Integer> implements
        IIntegerObject {
    /**
     * @param aOid
     * @param aName
     * @param aValue
     */
    public IntegerObject(OID aOid, String aName, Integer aValue) {
        super(aOid, aName, aValue);
    }

    public String toString() {
        return value.toString();
    }
}