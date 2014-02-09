package pl.edu.pjwstk.chemcalc.code;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MyParameterizedClassTest {

    private int multiplier;

    public MyParameterizedClassTest(int testParameter) {
        this.multiplier = testParameter;
    }

    // creates the test data
    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { { 1 }, { 5 }, { 11 } };
        return Arrays.asList(data);
    }

    @Test
    public void testMultiplyException() {
        MyMult tester = new MyMult();
        assertEquals("Result", multiplier * multiplier,
                tester.multiply(multiplier, multiplier));
    }
}