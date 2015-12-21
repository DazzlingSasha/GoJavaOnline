package ModuleLesson4.Task1;

public class Circle {
    private int radius;
    public Circle(int radius){
        this.radius = radius;
    }
    public double area(){
        return Math.round(Math.PI*(Math.pow(2, radius)));
    }
}
