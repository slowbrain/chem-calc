package pl.edu.pjwstk.chemcalc.code.interpreter.envs;

import java.util.ArrayList;
import java.util.Collection;

public class ENVSFrame implements IENVSFrame {
    private Collection<IENVSBinder> elements;

    public ENVSFrame() {
        elements = new ArrayList<IENVSBinder>();
    }

    public ENVSFrame(Collection<IENVSBinder> theElements) {
        elements = theElements;
    }

    public Collection<IENVSBinder> getElements() {
        return elements;
    }
}