package ModuleLesson4.Task2;

/**
 * Created by Dazzling on 21.12.15.
 */
public class Celsius {
    private int temp;

    public Celsius(int temp){
        this.temp = temp;
    }
    public double ConversionFromFahrenheitToCelsius(){
        return temp*1.8d +32;
    }
}
