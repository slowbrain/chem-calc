package pl.edu.pjwstk.chemcalc.code.interpreter.envs;

import pl.edu.pjwstk.chemcalc.code.result.IAbstractQueryResult;

public interface IENVSBinder {
    public String getName();

    public IAbstractQueryResult getValue();
}
