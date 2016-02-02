package ModuleLesson4.Task3;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dazzling on 02.02.16.
 */
public class SolutionTest {

    @Test
    public void testDistance() throws Exception {
        Solution solution = new Solution(-2, -3, 2, 3);
        int result = (int) solution.distance();
        assertEquals(7, result);
    }
}