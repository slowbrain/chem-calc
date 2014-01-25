package pl.edu.pjwstk.chemcalc.code.datastore;

public class DoubleObject extends SimpleObject<Double> implements IDoubleObject {
    /**
     * @param aOid
     * @param aName
     * @param aValue
     */
    public DoubleObject(OID aOid, String aName, Double aValue) {
        super(aOid, aName, aValue);
    }
    
    public String toString() {
		return value.toString();
	}
}