package ModuleLesson4.Task1;

/**
 * Created by Dazzling on 21.12.15.
 */
public class Solution {
    public static void main(String[] args){
        Rectangle rectangle = new Rectangle(3, 5);
        System.out.println(rectangle.area());
        Circle circle = new Circle(3);
        System.out.println(circle.area());
        Triangle triangle = new Triangle(3, 4);
        System.out.println(triangle.area());
    }
}
