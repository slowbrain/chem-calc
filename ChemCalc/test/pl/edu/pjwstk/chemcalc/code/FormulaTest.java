package pl.edu.pjwstk.chemcalc.code;

import junit.framework.TestCase;
import pl.edu.pjwstk.chemcalc.code.model.Formula;

public class FormulaTest extends TestCase {
    public void testFormula() {
        Formula f1 = new Formula("H", "H", 1.00794);
        assertEquals(1.00794, f1.getMass());
    }

    public void testSetChemform() {
        Formula f1 = new Formula("H", "H", 1.00794);
        f1.setChemform("C");
        assertEquals("C", f1.getChemform());
    }
}