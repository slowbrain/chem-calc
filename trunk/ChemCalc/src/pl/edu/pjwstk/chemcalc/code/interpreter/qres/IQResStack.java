package pl.edu.pjwstk.chemcalc.code.interpreter.qres;

import pl.edu.pjwstk.chemcalc.code.result.IAbstractQueryResult;

public interface IQResStack {
    public IAbstractQueryResult pop();

    public void push(IAbstractQueryResult value);
}