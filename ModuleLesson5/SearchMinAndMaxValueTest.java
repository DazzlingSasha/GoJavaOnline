package ModuleLesson5;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dazzling on 02.02.16.
 */
public class SearchMinAndMaxValueTest {

    @Test
    public void testSearchMinAndMax() throws Exception {
        int[] array = {-77, 2, 3222, 66, 777, -234, -7, 55, -999, 355, 456, 2345, 11};
        int[] arr = {-999, 3222};
        SearchMinAndMaxValue searchMinAndMaxValue =new SearchMinAndMaxValue();
        int[] result = searchMinAndMaxValue.searchMinAndMax(array);

        assertArrayEquals(arr,result);
    }
}