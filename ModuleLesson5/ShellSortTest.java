package ModuleLesson5;

import org.junit.Test;

import static org.junit.Assert.*;


public class ShellSortTest {

    @Test
    public void testSort() throws Exception {
        ShellSort ss = new ShellSort();
        int[] array = {-77, 2, 3222, 66, 777, -234, -7, 55, -999, 355, 456, 2345, 11};
        ss.sort(array);
        int[] array1 = {-999, -234, -77, -7, 2, 11, 55, 66, 355, 456, 777, 2345, 3222};
        assertArrayEquals("fff", array, array1);
    }
}