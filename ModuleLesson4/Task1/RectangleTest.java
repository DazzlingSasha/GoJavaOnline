package ModuleLesson4.Task1;

import org.junit.Test;

import static org.junit.Assert.*;


public class RectangleTest {

    @Test
    public void testArea() throws Exception {
        Rectangle rectangle = new Rectangle(3, 5);
        int result = rectangle.area();
        assertEquals(15, result);
    }
}