package ModuleLesson4.Task1;

/**
 * Created by Dazzling on 21.12.15.
 */
public class Rectangle {


    private int width;
    private int height;

    public Rectangle(int width, int height){
        this.width = width;
        this.height = height;
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int area(){
        return width*height;
    }
}
