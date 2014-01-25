package pl.edu.pjwstk.chemcalc.code.datastore;

@SuppressWarnings("serial")
public class OIDimpl implements OID {
    private Integer value;

    public OIDimpl(Integer aValue) {
        value = aValue;
    }

    public int GetValue() {
        return value;
    }
}