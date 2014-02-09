package pl.edu.pjwstk.chemcalc.code;

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyMultTest extends TestCase {

    @BeforeClass
    public static void testSetup() {
    }

    @AfterClass
    public static void testCleanup() {
        // Teardown for data used by the unit tests
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionIsThrown() {
        MyMult tester = new MyMult();
        tester.multiply(100, 7);
    }

    @Test
    public void testMultiply() {
        MyMult tester = new MyMult();
        assertEquals("100 x 7 must be 700", 700, tester.multiply(100, 7));
    }
}