package ModuleLesson4.Task1;

import org.junit.Test;

import static org.junit.Assert.*;

public class CircleTest {

    @Test
    public void testArea() throws Exception {
        Circle circle = new Circle(3);
        int result = (int) circle.area();
        assertEquals(25, result);
    }
}