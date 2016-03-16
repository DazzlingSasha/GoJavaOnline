package ForWork;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class FindMaxNumberOfUnitsTest {

    private static FindMaxNumberOfUnits e;

    @BeforeClass
    public static void setUp() {
        e = new FindMaxNumberOfUnits();
    }
    @Test (timeout = 2000)
    public void testInThisArray() throws Exception {
        int[] input = new int[]{15, 2, 3, 7, 5, 16};
        int result = e.inThisArray(input);
        assertEquals(result, 15);
    }

    @Test (timeout = 2000)
    public void testFromZeroToTheNumberOf() throws Exception {
        int result = e.fromZeroToTheNumberOf(6);
        assertEquals(result, 3);
     }

}