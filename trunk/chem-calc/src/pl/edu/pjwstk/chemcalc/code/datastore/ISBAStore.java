package pl.edu.pjwstk.chemcalc.code.datastore;

import java.util.Collection;

public interface ISBAStore {
    public ISBAObject retrieve(OID oid);

    public OID getEntryOID();

    public void loadXML(String filePath);

    public OID generateUniqueOID();

    public void addJavaObject(Object o, String objectName);

    public void addJavaCollection(Collection<?> o, String collectionName);
}