package pl.edu.pjwstk.chemcalc.code.interpreter.envs;

import pl.edu.pjwstk.chemcalc.code.datastore.ISBAObject;
import pl.edu.pjwstk.chemcalc.code.result.IAbstractQueryResult;

/**
 * @author s9111_s10304
 * 
 */
public class ENVSBinder implements IENVSBinder {
    protected String name;
    protected IAbstractQueryResult value;

    public ENVSBinder(String aName, IAbstractQueryResult aValue) {
        name = aName;
        value = aValue;
    }

    public ENVSBinder(ISBAObject childObj) {
        // TODO Auto-generated constructor stub
    }

    public String getName() {
        return name;
    }

    public IAbstractQueryResult getValue() {
        return value;
    }
}