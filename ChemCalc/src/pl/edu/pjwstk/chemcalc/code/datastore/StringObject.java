package pl.edu.pjwstk.chemcalc.code.datastore;

public class StringObject extends SimpleObject<String> implements IStringObject {
    /**
     * @param aOid
     * @param aName
     * @param aValue
     */
    public StringObject(OID aOid, String aName, String aValue) {
        super(aOid, aName, aValue);
    }

    public String toString() {
        return "\"" + value + "\"";
    }
}