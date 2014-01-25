package pl.edu.pjwstk.chemcalc.code.datastore;

public class SimpleObject<T> extends SBAObject implements ISimpleObject<T> {

    protected T value;

    /**
     * @param aOid
     * @param aName
     * @param aValue
     */
    public SimpleObject(OID aOid, String aName, T aValue) {
        super(aOid, aName);
        value = aValue;
    }

    public T getValue() {
        return value;
    }
}