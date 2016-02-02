package ModuleLesson4.Task1;

import org.junit.Test;

import static org.junit.Assert.*;

public class TriangleTest {
    @Test
    public void testArea() throws Exception {
        Triangle triangle = new Triangle(2, 3);
        int result = (int) triangle.area();
        assertEquals(3, result);
    }
}