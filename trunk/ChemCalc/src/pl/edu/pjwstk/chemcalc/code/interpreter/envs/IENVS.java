package pl.edu.pjwstk.chemcalc.code.interpreter.envs;

import pl.edu.pjwstk.chemcalc.code.datastore.ISBAStore;
import pl.edu.pjwstk.chemcalc.code.datastore.OID;
import pl.edu.pjwstk.chemcalc.code.result.IAbstractQueryResult;

public interface IENVS {
    public void init(OID rootOID, ISBAStore store);

    public IENVSFrame pop();

    public void push(IENVSFrame frame);

    public IENVSFrame nested(IAbstractQueryResult result, ISBAStore store);
}
