package pl.edu.pjwstk.chemcalc.code.datastore;

import java.io.Serializable;

public interface OID extends Serializable {
    public boolean equals(Object obj);

    public int hashCode();
}