package CoursesEE.ModuleNumberFour;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class SumElementsArrayTest {
    static SumElementsArray ser;
    @Before
    public void setUp() throws Exception {
        ser = new SumElementsArray();
    }
    @Test
    public void testGetSquareSum() throws Exception {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        long actual = 385;
        long expected = ser.getSquareSum(arr, 3);

        assertEquals("Test number One: ",expected, actual);
    }
    @Test
    public void testGetSquareSumArrayNull() throws Exception {
        int[] arr = {};
        long actual = 0;
        long expected = ser.getSquareSum(arr, 3);

        assertEquals("Test number Two: ",expected, actual);
    }
}