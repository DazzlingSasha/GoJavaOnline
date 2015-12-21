package ModuleLesson4.Task1;

/**
 * Created by Dazzling on 21.12.15.
 */
public class Triangle {


    private int widthA;
    private int heightB;


    public Triangle(int widthA, int height){
        this.widthA = widthA;
        this.heightB = height;
    }
    public int getWidthA() {
        return widthA;
    }

    public int getHeightB() {
        return heightB;
    }

    public double area(){
        return (widthA* heightB)/2 ;
    }
}
