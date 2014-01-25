package pl.edu.pjwstk.chemcalc.code.interpreter.envs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import pl.edu.pjwstk.chemcalc.code.datastore.IComplexObject;
import pl.edu.pjwstk.chemcalc.code.datastore.ISBAObject;
import pl.edu.pjwstk.chemcalc.code.datastore.ISBAStore;
import pl.edu.pjwstk.chemcalc.code.datastore.OID;
import pl.edu.pjwstk.chemcalc.code.result.IAbstractQueryResult;

public class ENVS implements IENVS {
    private LinkedList<IENVSFrame> frameStack;

    public ENVS() {
        frameStack = new LinkedList<IENVSFrame>();
    }

    public IENVSFrame pop() {
        return (frameStack.size() == 0) ? null : frameStack.pop();
    }

    public void push(IENVSFrame frame) {
        frameStack.push(frame);
    }

    public void init(OID rootOID, ISBAStore store) {
        IComplexObject entryObject = (IComplexObject) store.retrieve(rootOID);
        IENVSFrame frame = createENVSFrame(entryObject, store);
        push(frame);
    }

    private IENVSFrame createENVSFrame(IComplexObject obj, ISBAStore store) {
        Collection<IENVSBinder> frameElements = new ArrayList<IENVSBinder>();

        for (OID childOID : obj.getChildOIDs()) {
            IENVSBinder binder = createENVSBinder(childOID, store);
            frameElements.add(binder);
        }

        return new ENVSFrame(frameElements);
    }

    private IENVSBinder createENVSBinder(OID childOID, ISBAStore store) {
        ISBAObject childObj = store.retrieve(childOID);
        IENVSBinder binder = new ENVSBinder(childObj);
        return binder;
    }

    @Override
    public IENVSFrame nested(IAbstractQueryResult result, ISBAStore store) {
        return null;
    }
}