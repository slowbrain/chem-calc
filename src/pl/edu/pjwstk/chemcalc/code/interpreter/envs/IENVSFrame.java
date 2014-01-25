package pl.edu.pjwstk.chemcalc.code.interpreter.envs;

import java.util.Collection;

public interface IENVSFrame {
    public Collection<IENVSBinder> getElements();
}