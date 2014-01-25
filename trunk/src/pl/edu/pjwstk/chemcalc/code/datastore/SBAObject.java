package pl.edu.pjwstk.chemcalc.code.datastore;

public class SBAObject implements ISBAObject {

    protected OID oid;
    protected String name;

    /**
     * @param aOid
     * @param aName
     */
    public SBAObject(OID aOid, String aName) {
        oid = aOid;
        name = aName;
    }

    public OID getOID() {
        return oid;
    }

    public String getName() {
        return name;
    }
}