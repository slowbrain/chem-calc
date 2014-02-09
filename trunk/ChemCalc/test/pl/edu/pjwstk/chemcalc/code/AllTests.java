package pl.edu.pjwstk.chemcalc.code;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {
    public static Test suite() {
        TestSuite suite = new TestSuite("pl.edu.pjwstk.chemcalc.code");
        // $JUnit-BEGIN$
        suite.addTestSuite(FormulaTest.class);
        suite.addTestSuite(MyMultTest.class);
        // $JUnit-END$
        return suite;
    }
}