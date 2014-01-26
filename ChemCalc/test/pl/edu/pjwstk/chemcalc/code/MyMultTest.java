package pl.edu.pjwstk.chemcalc.code;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyMultTest {

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
        tester.multiply(1000, 5);
    }

    @Test
    public void testMultiply() {
        MyMult tester = new MyMult();
        assertEquals("10 x 5 must be 50", 50, tester.multiply(10, 5));
    }
}