package pl.edu.pjwstk.chemcalc.code.datastore;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class SBAStore implements ISBAStore {
    private Integer nextId = 0;
    private OID entryOID;
    private Map<OID, ISBAObject> SBAObjects;
    private String toString = "";

    public SBAStore() {
        SBAObjects = new HashMap<OID, ISBAObject>();
        IComplexObject object = new ComplexObject(generateUniqueOID(), "entry",
                new ArrayList<OID>());
        entryOID = object.getOID();
        addSBAObject(object, null);
    }

    @Override
    public ISBAObject retrieve(OID oid) {
        return SBAObjects.get(oid);
    }

    @Override
    public OID getEntryOID() {
        return entryOID;
    }

    @Override
    public void loadXML(String filePath) {
        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            InputStream inputStream = new FileInputStream(filePath);
            Document document = saxBuilder.build(inputStream);

            Element element = document.getRootElement();

            IComplexObject parent = (IComplexObject) retrieve(getEntryOID());

            if (!element.getChildren().isEmpty())
                parseXML(element, parent);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void parseXML(Element childNodes, IComplexObject parent) {
        for (Element child : childNodes.getChildren()) {
            loadNode(child, parent);
        }
    }

    public void loadNode(Element node, IComplexObject parent) {
        if (node.getChildren().isEmpty()) {
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(node.getText());

            if (scanner.hasNextBoolean()) {
                Create(SBAType.BOOLEAN, node.getName(), scanner.nextBoolean(),
                        parent.getOID());
            } else if (scanner.hasNextInt()) {
                Create(SBAType.INTEGER, node.getName(), scanner.nextInt(),
                        parent.getOID());
            } else if (scanner.hasNextDouble()) {
                Create(SBAType.DOUBLE, node.getName(), scanner.nextDouble(),
                        parent.getOID());
            } else {
                Create(SBAType.STRING, node.getName(), node.getText(),
                        parent.getOID());
            }
        } else {
            SBAObject object = Create(SBAType.COMPLEX, node.getName(),
                    new ArrayList<OID>(), parent.getOID());
            parent = (IComplexObject) retrieve(object.getOID());

            for (Element child : node.getChildren()) {
                loadNode(child, parent);
            }
        }
    }

    @Override
    public OID generateUniqueOID() {
        OIDimpl oid = new OIDimpl(nextId);
        nextId++;
        return oid;
    }

    private void addSBAObject(ISBAObject object, IComplexObject parent) {
        if (parent != null)
            parent.getChildOIDs().add(object.getOID());

        SBAObjects.put(object.getOID(), object);
    }

    @Override
    public void addJavaObject(Object object, String objectName) {
        if (object == null)
            return;

        if (object instanceof Boolean) {
            Create(SBAType.BOOLEAN, objectName, (Boolean) object, getEntryOID());
        } else if (object instanceof Double) {
            Create(SBAType.DOUBLE, objectName, (Double) object, getEntryOID());
        } else if (object instanceof Integer) {
            Create(SBAType.INTEGER, objectName, (Integer) object, getEntryOID());
        } else if (object instanceof String) {
            Create(SBAType.STRING, objectName, (String) object, getEntryOID());
        } else {
            for (Field field : object.getClass().getFields()) {
                try {
                    addJavaObject(field.get(object), field.getName());
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void addJavaCollection(Collection collection, String collectionName) {
        for (Object object : collection) {
            addJavaObject(object, collectionName);
        }
    }

    @Override
    public String toString() {
        return StringUtils.join(SBAObjects.values(), "\n");
    }

    public String getString() {
        return toString;
    }

    public void PrintObjects(ISBAObject object) {
        if (object instanceof ISimpleObject)
            if (object instanceof IStringObject)
                toString += "<" + ((OIDimpl) object.getOID()).GetValue() + ", "
                        + ((ISimpleObject<?>) object).getName() + ", \""
                        + ((ISimpleObject<?>) object).getValue().toString()
                        + "\">\n";
            else
                toString += "<" + ((OIDimpl) object.getOID()).GetValue() + ", "
                        + ((ISimpleObject<?>) object).getName() + ", "
                        + ((ISimpleObject<?>) object).getValue().toString()
                        + ">\n";
        else {
            if (((IComplexObject) object).getName() == "entry")
                toString = "";

            List<OID> childOIDs = ((IComplexObject) object).getChildOIDs();

            toString += "<"
                    + ((OIDimpl) ((IComplexObject) object).getOID()).GetValue()
                    + ", " + ((IComplexObject) object).getName() + ", {";

            for (int i = 0; i < childOIDs.size(); i++) {
                toString += ((OIDimpl) childOIDs.get(i)).GetValue() + ", ";
            }

            toString = toString.substring(0, toString.length() - 2);

            toString += "}>\n";

            for (int i = 0; i < childOIDs.size(); i++) {
                PrintObjects(retrieve(childOIDs.get(i)));
            }
        }
    }

    @SuppressWarnings("unchecked")
    public SBAObject Create(SBAType type, String name, Object value,
            OID parentOID) {
        SBAObject obj = null;
        switch (type) {
        case BOOLEAN:
            obj = new BooleanObject(generateUniqueOID(), name, (Boolean) value);
            addSBAObject(obj, (IComplexObject) retrieve(parentOID));
            break;
        case INTEGER:
            obj = new IntegerObject(generateUniqueOID(), name, (Integer) value);
            addSBAObject(obj, (IComplexObject) retrieve(parentOID));
            break;
        case DOUBLE:
            obj = new DoubleObject(generateUniqueOID(), name, (Double) value);
            addSBAObject(obj, (IComplexObject) retrieve(parentOID));
            break;
        case STRING:
            obj = new StringObject(generateUniqueOID(), name, (String) value);
            addSBAObject(obj, (IComplexObject) retrieve(parentOID));
            break;
        case COMPLEX:
            obj = new ComplexObject(generateUniqueOID(), name,
                    ((ArrayList<OID>) value));
            addSBAObject(obj, (IComplexObject) retrieve(parentOID));
            break;
        default:
            System.out.println("Nie obsługiwany typ.\n");
            break;
        }
        return obj;
    }
}